package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Transaction;

public class TransactionDTO extends ConvertibleDTO<Transaction>{

	public Long id;
	//account number:
	public String sender;
	public String receiver;
	public Long amount;
	public String message;

	public TransactionDTO() {
	}

	public TransactionDTO(Transaction entity) {
		//super(entity);
	}

	@Override
	Transaction toEntity() {
		return null;
	}
}
