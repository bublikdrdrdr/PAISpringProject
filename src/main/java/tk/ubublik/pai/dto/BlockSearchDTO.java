package tk.ubublik.pai.dto;

import org.springframework.data.domain.Pageable;

public class BlockSearchDTO extends SearchDTO<BlockDTO> {
	@Override
	public Pageable getPageable() {
		return null;
	}
}
