package tk.ubublik.pai.dto;

import org.springframework.data.domain.Pageable;

public class TransactionSearchDTO extends SearchDTO<TransactionDTO> {

	//account numbers:
	public String sender;
	public String receiver;
	public String account;
	public boolean strict = false;

	public Long min;
	public Long max;
}
