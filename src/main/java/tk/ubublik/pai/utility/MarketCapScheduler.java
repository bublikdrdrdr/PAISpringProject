package tk.ubublik.pai.utility;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketCapScheduler {

    @Scheduled(fixedRate = 1000*60*60)
    public void calculateMarketCap(){
        //TODO
    }
}
