package tk.ubublik.pai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.ubublik.pai.entity.Role;
import tk.ubublik.pai.entity.User_;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.entity.User;
import tk.ubublik.pai.repository.UserRepository;
import tk.ubublik.pai.validation.UserValidator;
import tk.ubublik.pai.validation.Validator;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator validator;

    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public Errors registerUser(UserDTO userDTO) {
        Errors errors = Errors.emptyInstance();
        User user = userDTO.toEntity();
        errors.addAll(validateEmail(user.getEmail()));
        errors.addAll(validateUsername(user.getUsername()));
        errors.addAll(validatePassword(user.getPassword()));
        Role role = validateRole(userDTO, errors);
        if (!errors.isEmpty()) return errors;
        user.setId(null);
        Date now = new Date();
        user.setPasswordChanged(now);
        user.setRegistered(now);
        user.setEnabled(true);
        if (role!=null && role!=Role.USER){
            if (!securityService.hasRole(Role.ADMIN)) throw new AccessDeniedException("Admin authority required");
        } else{
            role = Role.USER;
        }
        user.setRole(role);
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
    public Errors updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
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
