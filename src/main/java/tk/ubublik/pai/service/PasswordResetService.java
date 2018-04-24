package tk.ubublik.pai.service;

import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.UserDTO;

public interface PasswordResetService {

	Errors request(String email);
	Errors reset(String hash, String password);
}
