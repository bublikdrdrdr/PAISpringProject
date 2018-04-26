package tk.ubublik.pai.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tk.ubublik.pai.entity.Account_;
import tk.ubublik.pai.entity.Block_;

import java.util.Date;

public class BlockSearchDTO extends SearchDTO<BlockDTO> {

	public Long userId;
	public Long adminId;
	public Boolean active;

	@Override
	public Pageable getPageable() {
		return getPageable(getSort());
	}

	private Sort getSort(){
		if (order == null) return null;
		final String[] allowedFields = new String[]{Block_.START, Block_.END, Block_.USER};
		for (String field: allowedFields){
			if (order.compareToIgnoreCase(field)==0) return Sort.by(getDirection(), field);
		}
		return null;
	}
}
