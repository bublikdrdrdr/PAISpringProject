package tk.ubublik.pai.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

public abstract class SearchDTO<D> {

	public int page;
	public int size;
	public String order;
	public boolean desc = false;

	public Pageable getPageable(){
		return getPageable(null);
	}

	protected Pageable getPageable(@Nullable Sort sort){
		if (sort==null)
			return PageRequest.of(page, size);
		else return PageRequest.of(page, size, sort);
	}

	public Sort.Direction getDirection(){
		return desc? Sort.Direction.DESC: Sort.Direction.ASC ;
	}
}
