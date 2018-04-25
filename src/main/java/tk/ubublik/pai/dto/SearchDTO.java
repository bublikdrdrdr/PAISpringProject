package tk.ubublik.pai.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class SearchDTO<D> {

	public int page;
	public int size;
	public String order;
	public boolean desc = false;

	public abstract Pageable getPageable();
	public Sort.Direction getDirection(){
		return desc? Sort.Direction.DESC: Sort.Direction.ASC ;
	}
}
