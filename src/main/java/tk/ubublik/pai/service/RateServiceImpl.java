package tk.ubublik.pai.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tk.ubublik.pai.dto.RateDTO;
import tk.ubublik.pai.dto.RateSearchDTO;
import tk.ubublik.pai.entity.Rate;
import tk.ubublik.pai.entity.Rate_;
import tk.ubublik.pai.repository.RateRepository;
import tk.ubublik.pai.validation.Errors;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class RateServiceImpl implements RateService {

	@Autowired
	private RateRepository rateRepository;

	@Override
	public Errors save(RateDTO rateDTO) {
		rateDTO.id = null;
		rateDTO.date = new Date();
		if (rateDTO.buying==null && rateDTO.selling==null) throw new NullPointerException("Buying or selling values can't be null");
		rateRepository.save(rateDTO.toEntity());
		return Errors.emptyInstance();
	}

	@Override
	public Errors removeLast() {
		Page<Rate> rates = rateRepository.findAll(PageRequest.of(0, 1, Sort.Direction.DESC, Rate_.DATE));
		if (rates.getTotalElements()>0)
			rateRepository.delete(rates.getContent().get(0));
		return Errors.emptyInstance();
	}

	@Override
	public byte[] getChart(Date from, Date to) throws IOException {
		List<RateDTO> results = search(new RateSearchDTO(from, to)).getContent();
		final XYSeries sell = new XYSeries("sell");
		final XYSeries buy = new XYSeries("buy");
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (RateDTO rateDTO : results) {
			sell.add(rateDTO.date.getTime() / 6000, rateDTO.selling);
			buy.add(rateDTO.date.getTime() / 6000, rateDTO.buying);
		}
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(sell);
		dataset.addSeries(buy);

		JFreeChart xylineChart = ChartFactory.createXYLineChart(
				"Rate stats",
				"date",
				"value",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false);
		xylineChart.getXYPlot().getDomainAxis().setVisible(false);
		BufferedImage img = xylineChart.createBufferedImage(1000, 500, BufferedImage.TYPE_3BYTE_BGR, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "png", baos);
		baos.flush();
		byte[] bytes = baos.toByteArray();
		baos.close();
		return bytes;
	}

	@Override
	public Page<RateDTO> search(RateSearchDTO searchDTO) {
		return rateRepository.findAll(searchDTO.from, searchDTO.to, searchDTO.getPageable()).map(RateDTO::new);
	}
}
