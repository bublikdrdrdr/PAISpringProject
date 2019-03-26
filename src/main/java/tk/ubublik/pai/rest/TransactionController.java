package tk.ubublik.pai.rest;

import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.TransactionDTO;
import tk.ubublik.pai.dto.TransactionSearchDTO;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @PostMapping
    public void create(@RequestBody TransactionDTO transaction) {

    }

    @GetMapping
    public List<TransactionDTO> searchTransactions(@RequestBody TransactionSearchDTO transactionSearch) {
        return null;
    }
}
