package tk.ubublik.pai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.entity.Role;
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
	public String getProfile(Model model, @RequestParam(required = false) Long id){
		if (id==null || !securityService.hasRole(Role.MODERATOR)){
			id = securityService.getAuthenticatedUser().getId();
		}
		UserDTO user = userService.getUserById(id);
		user.setPassword(null);
		model.addAttribute("user", user);
		return "profile";
	}

	@PostMapping("/profile")
	public String saveProfile(Model model, @ModelAttribute("user") UserDTO userDTO){
		model.addAttribute("errors", userService.updateUser(userDTO));
		return "profile";
	}

	@GetMapping("/users")
	public String getSearchUserPage(){
		return "users";
	}

	@PostMapping("/users")
	public String searchUser(Model model, @RequestParam(required = false ) String username){
		try{
			if (username==null) throw new UsernameNotFoundException("");
			UserDTO userDTO = userService.getUserByUsername(username);
			if (userDTO==null) userDTO = userService.getUserByEmail(username);
			if (userDTO==null) throw new UsernameNotFoundException("");
			return getProfile(model, userDTO.id);
		} catch (UsernameNotFoundException e){
			model.addAttribute("notFound");
			return "users";
		}
	}
}
