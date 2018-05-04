package tk.ubublik.pai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.service.UserService;
import tk.ubublik.pai.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}
