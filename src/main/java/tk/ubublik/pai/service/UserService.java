package tk.ubublik.pai.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetailsService;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.entity.User;

import javax.persistence.EntityNotFoundException;

public interface UserService extends UserDetailsService{

	UserDTO getUserById(long id) throws AccessDeniedException;
	Errors registerUser(UserDTO userDTO) throws AccessDeniedException;
	Errors validateEmail(String email);
	Errors validateUsername(String username);
	Errors validatePassword(String password);
	Errors updateUser(UserDTO userDTO) throws AccessDeniedException, EntityNotFoundException;
	UserDTO getUserByEmail(String email);
}
