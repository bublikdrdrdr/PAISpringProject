package tk.ubublik.pai.service;

import org.springframework.data.domain.Page;
import tk.ubublik.pai.dto.SearchDTO;

public interface SearchableService<D, S extends SearchDTO> {

	Page<D> search(S searchDTO);
}
