package tk.ubublik.pai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.ubublik.pai.entity.Role;
import tk.ubublik.pai.entity.User_;
import tk.ubublik.pai.repository.BlockRepository;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.entity.User;
import tk.ubublik.pai.repository.UserRepository;
import tk.ubublik.pai.validation.UserValidator;
import tk.ubublik.pai.validation.Validator;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserValidator validator;
    private SecurityService securityService;
    private PasswordEncoder passwordEncoder;
    private BlockRepository blockRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserValidator validator, SecurityService securityService,
                           PasswordEncoder passwordEncoder, BlockRepository blockRepository){
        this.userRepository = userRepository;
        this.validator = validator;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.blockRepository = blockRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        user.setBlocked(blockRepository.isUserBlocked(user, new Date()));
        return user;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public UserDTO getUserById(long id) throws AccessDeniedException {
        return new UserDTO(userRepository.getOne(id));
    }

    @Override
    public Errors registerUser(UserDTO userDTO) {
        Errors errors = Errors.emptyInstance();
        errors.addAll(validateEmail(userDTO.email));
        errors.addAll(validateUsername(userDTO.username));
        errors.addAll(validatePassword(userDTO.password));
        Role role = validateRole(userDTO, errors);
        if (!errors.isEmpty()) return errors;
        Date now = new Date();
        if (role!=null && role!=Role.USER){
            if (!securityService.hasRole(Role.ADMIN)) throw new AccessDeniedException("Admin authority required");
        } else role = Role.USER;
        User user = new User(userDTO.username, userDTO.email, passwordEncoder.encode(userDTO.password), now, now, true, role);
        userRepository.save(user);
        return errors;
    }

    @Override
    public Errors validateEmail(String email) {
        Errors errors = validator.validate(User_.EMAIL, email);
        if (errors.isEmpty() && userRepository.findByEmail(email)!=null)
            errors.add(Validator.ValidationHelper.unique(User_.EMAIL));
        return errors;
    }

    @Override
    public Errors validateUsername(String username) {
        Errors errors = validator.validate(User_.USERNAME, username);
        if (errors.isEmpty() && userRepository.findByUsername(username)!=null)
            errors.add(Validator.ValidationHelper.unique(User_.USERNAME));
        return errors;
    }

    @Override
    public Errors validatePassword(String password) {
        return validator.validate(User_.PASSWORD, password);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public Errors updateUser(UserDTO userDTO){
        Errors errors = Errors.emptyInstance();
        User authenticated = securityService.getAuthenticatedUser();
        User updatableUser = null;
        if (userDTO.id!=null && !userDTO.id.equals(authenticated.getId())){
            if (!securityService.hasRole(Role.MODERATOR))
                throw new AccessDeniedException("Moderator authority required");
            else
                updatableUser = userRepository.getOne(userDTO.id);
        }
        if (updatableUser==null) updatableUser = userRepository.getOne(authenticated.getId());
        Role updateRole;
        try {
            updateRole = userDTO.getRole();
        } catch (IllegalArgumentException e){
            errors.add(Validator.ValidationHelper.format("role"));
            return errors;
        }
        if (updateRole!=null){
            if (!securityService.hasRole(Role.ADMIN))
                throw new AccessDeniedException("Admin authority required");
            else updatableUser.setRole(updateRole);
        }
        if (userDTO.email!=null && !userDTO.email.equals(updatableUser.getEmail())) {
            errors.addAll(validateEmail(userDTO.email));
            updatableUser.setEmail(userDTO.email);
        }
        if (userDTO.password!=null && !userDTO.password.isEmpty()) {
            errors.addAll(validatePassword(userDTO.password));
            updatableUser.setPasswordChanged(new Date());
            updatableUser.setPassword(passwordEncoder.encode(userDTO.password));
        }
        if (errors.isEmpty()) userRepository.save(updatableUser);
        return errors;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        try {
            return new UserDTO(userRepository.findByEmail(email));
        } catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        try {
            return new UserDTO(userRepository.findByUsername(username));
        } catch (NullPointerException e){
            return null;
        }
    }

    private Role validateRole(UserDTO userDTO, Errors errors){
        try{
            return userDTO.getRole();
        } catch (IllegalArgumentException e){
            errors.add(Validator.ValidationHelper.format("role"));
            return null;
        }
    }
}
