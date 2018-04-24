package tk.ubublik.pai.service;

import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.MarketCapRecordDTO;
import tk.ubublik.pai.dto.RateDTO;
import tk.ubublik.pai.dto.RateSearchDTO;

public interface RateService extends SearchableService<RateDTO, RateSearchDTO> {

	Errors save(RateDTO rateDTO);
}
