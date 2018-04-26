package tk.ubublik.pai.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import tk.ubublik.pai.entity.Account_;

public class AccountSearchDTO extends SearchDTO<AccountDTO> {

	@Nullable
	public Long userId;

	@Override
	public Pageable getPageable() {
		return getPageable(getSort());
	}

	private Sort getSort(){
		if (order == null) return null;
		switch (order.toLowerCase()){
			case "name": return Sort.by(getDirection(), Account_.NAME);
			case "date": return Sort.by(getDirection(), Account_.CREATED);
			default: return null;
		}
	}
}
