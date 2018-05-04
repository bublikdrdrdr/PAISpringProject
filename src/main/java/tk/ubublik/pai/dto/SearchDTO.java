package tk.ubublik.pai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class SearchDTO<D> {

	public Integer page;
	public Integer size;
	public String order;
	public boolean desc = false;

	public Pageable getPageable(){
		return getPageable(null);
	}

	protected Pageable getPageable(@Nullable Sort sort){
		if (page==null) page = 0;
		if (size==null) size = 50;
		if (sort==null)
			return PageRequest.of(page, size);
		else return PageRequest.of(page, size, sort);
	}

	public Sort.Direction getDirection(){
		return desc? Sort.Direction.DESC: Sort.Direction.ASC ;
	}
}
