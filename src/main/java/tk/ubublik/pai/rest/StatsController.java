package tk.ubublik.pai.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.ubublik.pai.dto.RateDTO;
import tk.ubublik.pai.service.RateService;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final RateService rateService;

    @GetMapping(value = "/chart", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(
            @RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to
    ) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(rateService.getChart(from, to), headers, HttpStatus.OK);
    }

    @PostMapping
    public void addRate(@RequestBody RateDTO rate) {
        rateService.save(rate);
    }
}
