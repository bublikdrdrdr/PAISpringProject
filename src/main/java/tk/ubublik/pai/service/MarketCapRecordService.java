package tk.ubublik.pai.service;

import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.MarketCapRecordDTO;
import tk.ubublik.pai.dto.MarketCapRecordSearchDTO;

public interface MarketCapRecordService extends SearchableService<MarketCapRecordDTO, MarketCapRecordSearchDTO> {

	Errors save(MarketCapRecordDTO marketCapRecordDTO);
}
