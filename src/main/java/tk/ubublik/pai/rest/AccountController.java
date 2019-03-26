package tk.ubublik.pai.rest;

import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.AccountDTO;
import tk.ubublik.pai.dto.AccountSearchDTO;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @PutMapping
    public void addAccount(@RequestBody AccountDTO account) {

    }

    @GetMapping
    public List<AccountDTO> getAccounts(@RequestBody AccountSearchDTO accountSearch) {
        return null;
    }
}
