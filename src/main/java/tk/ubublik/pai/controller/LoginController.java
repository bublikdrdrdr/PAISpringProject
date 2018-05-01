package tk.ubublik.pai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.service.UserService;
import tk.ubublik.pai.validation.Errors;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@GetMapping("/registration")
	public String registrationForm(Model model) {
		model.addAttribute("user", new UserDTO());
		return "registration";
	}

	@PostMapping(value = "/registration")
	public String register(Model model, @ModelAttribute UserDTO user) {
		Errors errors = userService.registerUser(user);
		if (errors.isEmpty()) return "login";
		model.addAttribute("registrationError", true);
		model.addAttribute("errors", errors);
		model.addAttribute("user", user);
		return "registration";
	}
}
