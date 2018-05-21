package tk.ubublik.pai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.RateDTO;
import tk.ubublik.pai.service.RateService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class StatsController {

	@Autowired
	private RateService rateService;

	@GetMapping("/stats")
	public String getStats(Model model,
	                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
	                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to){

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		if (to==null) to = calendar.getTime();
		if (from==null) {
			calendar.setTime(to);
			calendar.add(Calendar.MONTH, -1);
			from = calendar.getTime();
		}
		String fromAttribute = new SimpleDateFormat("yyyy-MM-dd").format(from);
		String toAttribute = new SimpleDateFormat("yyyy-MM-dd").format(to);
		model.addAttribute("from", fromAttribute);
		model.addAttribute("to", toAttribute);
		model.addAttribute("imageUrl", "/stats/chart?from="+fromAttribute+"&to="+toAttribute);
		return "stats";
	}

	@RequestMapping(value = "/stats/chart", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to
	) throws IOException {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(rateService.getChart(from, to), headers, HttpStatus.OK);
	}

	@PostMapping("/stats")
	@PreAuthorize("hasRole('ADMIN')")
	public String addRate(Model model, @RequestParam double buying, @RequestParam double selling){
		rateService.save(new RateDTO(null, new Date(), buying, selling));
		return getStats(model, null, null);
	}

	@PostMapping("/stats/remove")
	@PreAuthorize("hasRole('ADMIN')")
	public String removeLastRate(Model model){
		rateService.removeLast();
		return getStats(model, null, null);
	}
}
