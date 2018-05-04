package tk.ubublik.pai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import tk.ubublik.pai.dto.AccountDTO;
import tk.ubublik.pai.dto.AccountSearchDTO;
import tk.ubublik.pai.dto.TransactionSearchDTO;
import tk.ubublik.pai.entity.Role;
import tk.ubublik.pai.service.AccountService;
import tk.ubublik.pai.service.SecurityService;
import tk.ubublik.pai.service.TransactionService;
import tk.ubublik.pai.service.UserService;
import tk.ubublik.pai.validation.Errors;

@Controller
public class AccountController {

	private UserService userService;
	private AccountService accountService;
	private TransactionService transactionService;
	private SecurityService securityService;

	@Autowired
	public AccountController(UserService userService, AccountService accountService,
	                         TransactionService transactionService, SecurityService securityService) {
		this.userService = userService;
		this.accountService = accountService;
		this.transactionService = transactionService;
		this.securityService = securityService;
	}

	@GetMapping(value = {"/", "/index", "/accounts"})
	public String getMyAccounts(Model model,
	                    @RequestParam(required = false, defaultValue = "name") String order,
	                    @RequestParam(required = false) String desc){
		model.addAttribute("accounts", accountService.search(new AccountSearchDTO(0, 10, order,
				desc!=null&&!desc.equals("false"), securityService.getAuthenticatedUser().getId())));
		return "index";
	}

	@RequestMapping("/users/{id}/accounts")
	public String getAccounts(Model model, @PathVariable("id") long id,
	                          @RequestParam(required = false, defaultValue = "name") String order,
	                          @RequestParam(required = false) String desc) {
		model.addAttribute("accounts", accountService.search(new AccountSearchDTO(0, 10, order,
				desc != null && !desc.equals("false"), securityService.getAuthenticatedUser().getId())));
		model.addAttribute("user", userService.getUserById(id));
		return "index";
	}

	@PostMapping("/")
	public ModelAndView addAccount(Model model, @RequestParam String name, @RequestParam(required = false) Long id){
		if (id!=null && !securityService.hasRole(Role.MODERATOR)) throw new AccessDeniedException("Moderator authorities required");
		Errors errors = accountService.addAccount(new AccountDTO(securityService.getAuthenticatedUser().getId(), name, null, null));
		if (errors.isEmpty()) return new ModelAndView("redirect:/");
		if (id!=null) model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("mode", "add");
		model.addAttribute("errors", errors);
		return new ModelAndView("redirect:/");
	}

}
