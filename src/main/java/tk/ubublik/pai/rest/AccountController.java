package tk.ubublik.pai.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.AccountDTO;
import tk.ubublik.pai.dto.AccountSearchDTO;
import tk.ubublik.pai.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PutMapping
    public void addAccount(@RequestBody AccountDTO account) {
        accountService.addAccount(account);
    }

    @GetMapping
    public List<AccountDTO> getAccounts(@RequestBody AccountSearchDTO accountSearch) {
        return accountService.search(accountSearch).getContent();
    }
}
