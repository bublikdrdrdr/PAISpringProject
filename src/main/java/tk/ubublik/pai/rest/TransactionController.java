package tk.ubublik.pai.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.TransactionDTO;
import tk.ubublik.pai.dto.TransactionSearchDTO;
import tk.ubublik.pai.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public void create(@RequestBody TransactionDTO transaction) {
        transactionService.save(transaction);
    }

    @GetMapping
    public List<TransactionDTO> searchTransactions(@RequestBody TransactionSearchDTO transactionSearch) {
        return transactionService.search(transactionSearch).getContent();
    }
}
