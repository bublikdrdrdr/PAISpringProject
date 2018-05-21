package tk.ubublik.pai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.ubublik.pai.entity.Rate;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDTO extends ConvertibleDTO<Rate> {

	public Long id;
	public Date date;
	public Double buying;
	public Double selling;

	public RateDTO(Rate entity) {
		this(entity.getId(), entity.getDate(), entity.getBuying(), entity.getSelling());
	}

	@Override
	public Rate toEntity() {
		return new Rate(id, date, buying, selling);
	}
}
