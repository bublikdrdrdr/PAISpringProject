package tk.ubublik.pai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSearchDTO extends SearchDTO<TransactionDTO> {

	//account numbers:
	public String sender;
	public String receiver;
	public String account;
	public boolean strict = false;

	public TransactionSearchDTO(Integer page, Integer size, String order, boolean desc, String sender, String receiver, String account, boolean strict, Long min, Long max) {
		super(page, size, order, desc);
		this.sender = sender;
		this.receiver = receiver;
		this.account = account;
		this.strict = strict;
		this.min = min;
		this.max = max;
	}

	public Long min;
	public Long max;
}
