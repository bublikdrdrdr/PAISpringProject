package tk.ubublik.pai.service;

import org.springframework.data.domain.Page;
import tk.ubublik.pai.entity.TransactionStatus;
import tk.ubublik.pai.exception.AccountNumberChecksumException;
import tk.ubublik.pai.exception.AccountNumberFormatException;
import tk.ubublik.pai.exception.TransactionException;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.MarketCapRecordDTO;
import tk.ubublik.pai.dto.TransactionDTO;
import tk.ubublik.pai.dto.TransactionSearchDTO;
import tk.ubublik.pai.entity.Transaction;

public interface TransactionService extends SearchableService<TransactionDTO, TransactionSearchDTO> {

	@Override
	Page<TransactionDTO> search(TransactionSearchDTO searchDTO) throws AccountNumberFormatException, AccountNumberChecksumException;
	Errors save(TransactionDTO transactionDTO) throws TransactionException;
	Errors setStatus(long transactionId, TransactionStatus status);
	long getAccountAvailableBalance(String accountNumber) throws AccountNumberChecksumException, AccountNumberFormatException;
}
