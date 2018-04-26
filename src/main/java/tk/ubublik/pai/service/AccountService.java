package tk.ubublik.pai.service;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import tk.ubublik.pai.dto.AccountDTO;
import tk.ubublik.pai.dto.AccountSearchDTO;
import tk.ubublik.pai.entity.Account;
import tk.ubublik.pai.entity.User;
import tk.ubublik.pai.validation.Errors;

public interface AccountService extends SearchableService<AccountDTO, AccountSearchDTO> {

	Errors addAccount(AccountDTO accountDTO);
	Errors validateAccountName(AccountDTO accountDTO);
	boolean validateAccountNumber(String accountNumber);
	Errors updateAccount(AccountDTO accountDTO);
}
