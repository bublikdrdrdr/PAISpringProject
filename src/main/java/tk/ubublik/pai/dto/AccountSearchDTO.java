package tk.ubublik.pai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import tk.ubublik.pai.entity.Account_;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSearchDTO extends SearchDTO<AccountDTO> {

	@Nullable
	public Long userId;

	public AccountSearchDTO(int page, int size, String order, boolean desc, Long userId) {
		super(page, size, order, desc);
		this.userId = userId;
	}

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
