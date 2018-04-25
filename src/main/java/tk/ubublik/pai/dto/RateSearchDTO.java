package tk.ubublik.pai.dto;

import org.springframework.data.domain.Pageable;

public class RateSearchDTO extends SearchDTO<RateDTO> {
	@Override
	public Pageable getPageable() {
		return null;
	}
}
