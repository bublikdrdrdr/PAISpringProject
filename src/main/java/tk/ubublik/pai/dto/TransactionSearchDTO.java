package tk.ubublik.pai.dto;

import org.springframework.data.domain.Pageable;

public class TransactionSearchDTO extends SearchDTO<TransactionDTO> {
	@Override
	public Pageable getPageable() {
		return null;
	}
}
