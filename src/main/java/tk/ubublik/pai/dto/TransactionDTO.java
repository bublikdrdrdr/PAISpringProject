package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Transaction;
import tk.ubublik.pai.entity.TransactionStatus;

import java.util.Date;

public class TransactionDTO extends ConvertibleDTO<Transaction>{

	public Long id;
	//account number:
	public String sender;
	public String receiver;
	public Long amount;
	public Date date;
	public String message;
	public String status;

	public TransactionDTO() {
	}

	public TransactionDTO(Long id, String sender, String receiver, Long amount, Date date, String message, String status) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.date = date;
		this.message = message;
		this.status = status;
	}

	public TransactionDTO(Transaction entity) {
		this(entity.getId(), getAccountNumberFromAccount(entity.getSender()), getAccountNumberFromAccount(entity.getReceiver()),
				entity.getAmount(), entity.getDate(), entity.getMessage(), entity.getStatus().name().toLowerCase());
	}

	@Override
	Transaction toEntity() {
		return new Transaction(id, getFromNumber(sender), getFromNumber(receiver), amount, message,  date, getTransactionStatus());
	}

	public TransactionStatus getTransactionStatus(){
		try{
			return TransactionStatus.valueOf(status.toUpperCase());
		} catch (Exception e){
			return null;
		}
	}
}
