package tk.ubublik.pai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.service.SecurityService;
import tk.ubublik.pai.service.UserService;

@Controller
public class UserController {

	private SecurityService securityService;
	private UserService userService;

	@Autowired
	public UserController(SecurityService securityService, UserService userService) {
		this.securityService = securityService;
		this.userService = userService;
	}

	@GetMapping("/profile")
	public String getProfile(Model model){
		model.addAttribute("user", userService.getUserById(securityService.getAuthenticatedUser().getId()));
		return "profile";
	}

	@PostMapping("/profile")
	public String saveProfile(Model model, @ModelAttribute("user") UserDTO userDTO){
		model.addAttribute("errors", userService.updateUser(userDTO));
		return "profile";
	}
}
