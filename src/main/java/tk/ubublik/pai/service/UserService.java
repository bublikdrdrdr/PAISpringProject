package tk.ubublik.pai.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.entity.User;

public interface UserService extends UserDetailsService{

	Errors registerUser(UserDTO userDTO);
	Errors validateEmail(String email);
	Errors validateUsername(String username);
	Errors validatePassword(String password);
	Errors updateUser(UserDTO userDTO);
	User getUserByEmail(String email);
}
