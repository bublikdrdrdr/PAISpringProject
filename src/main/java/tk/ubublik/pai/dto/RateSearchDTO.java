package tk.ubublik.pai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateSearchDTO extends SearchDTO<RateDTO> {

	public Date from, to;

	public RateSearchDTO(Integer page, Integer size, String order, boolean desc, Date from, Date to) {
		super(page, size, order, desc);
		this.from = from;
		this.to = to;
	}
}
