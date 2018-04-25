package tk.ubublik.pai.dto;

import org.springframework.data.domain.Pageable;

public class MarketCapRecordSearchDTO extends SearchDTO<MarketCapRecordDTO> {
	@Override
	public Pageable getPageable() {
		return null;
	}
}
