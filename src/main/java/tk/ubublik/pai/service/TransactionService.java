package tk.ubublik.pai.service;

import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.MarketCapRecordDTO;
import tk.ubublik.pai.dto.TransactionDTO;
import tk.ubublik.pai.dto.TransactionSearchDTO;
import tk.ubublik.pai.entity.Transaction;

public interface TransactionService extends SearchableService<TransactionDTO, TransactionSearchDTO> {

	Errors save(TransactionDTO transactionDTO);
}
