package tk.ubublik.pai.service;

import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.MarketCapRecordDTO;
import tk.ubublik.pai.dto.RateDTO;
import tk.ubublik.pai.dto.RateSearchDTO;

import java.io.IOException;
import java.util.Date;

public interface RateService extends SearchableService<RateDTO, RateSearchDTO> {

	Errors save(RateDTO rateDTO);
	Errors removeLast();
	byte[] getChart(Date from, Date to) throws IOException;
}
