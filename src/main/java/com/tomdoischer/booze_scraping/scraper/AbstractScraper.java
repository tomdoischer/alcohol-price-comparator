package com.tomdoischer.booze_scraping.scraper;

import com.tomdoischer.booze_scraping.stores.Store;
import com.tomdoischer.booze_scraping.entity.WhiskyBottle;
import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;
import com.tomdoischer.booze_scraping.service.WhiskyBottleService;
import com.tomdoischer.booze_scraping.service.WhiskyBottleUpdateService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AbstractScraper {

    private final WhiskyBottleUpdateService whiskyBottleUpdateService;
    private final WhiskyBottleService whiskyBottleService;

    protected AbstractScraper(WhiskyBottleUpdateService whiskyBottleUpdateService, WhiskyBottleService whiskyBottleService) {
        this.whiskyBottleUpdateService = whiskyBottleUpdateService;
        this.whiskyBottleService = whiskyBottleService;
    }

    public void scrape() throws IOException {
        List<String> urlsToParse = getUrlsToParse();
        List<WhiskyBottleUpdate> whiskyBottleUpdates = new ArrayList<>();
        for (String url : urlsToParse) {
            processOnePage(whiskyBottleUpdates, url);
        }

        System.out.println("");
    }

    protected abstract List<String> getUrlsToParse() throws IOException;

    protected abstract void processOnePage(List<WhiskyBottleUpdate> updates, String url) throws IOException;

    protected WhiskyBottleUpdate createWhiskyBottleUpdate(double price, boolean availability,
                                                          String name, String link, Store store) {
        WhiskyBottleUpdate whiskyBottleUpdate = new WhiskyBottleUpdate();
        whiskyBottleUpdate.setName(name);
        whiskyBottleUpdate.setPrice(price);
        whiskyBottleUpdate.setLink(link);
        whiskyBottleUpdate.setInStock(availability);
        whiskyBottleUpdate.setStore(store);
        whiskyBottleUpdate.setWhiskyBottle(getWhiskyBottle(whiskyBottleUpdate));
        whiskyBottleUpdateService.save(whiskyBottleUpdate);
        return whiskyBottleUpdate;
    }

    protected WhiskyBottle getWhiskyBottle(WhiskyBottleUpdate whiskyBottleUpdate) {
//        if (whiskyBottleUpdate.getWhiskyBottle() == null) {
//            whiskyBottleService.findWhiskyBottle(whiskyBottleUpdate.getName())
//                    .ifPresent(whiskyBottleUpdate::setWhiskyBottle);
//        }
        return whiskyBottleService.findWhiskyBottle(whiskyBottleUpdate.getName()).orElseGet(() -> {
            WhiskyBottle newWhiskyBottle = new WhiskyBottle();
            newWhiskyBottle.setName(whiskyBottleUpdate.getName());
            newWhiskyBottle = whiskyBottleService.save(newWhiskyBottle);
            return newWhiskyBottle;
        });
    }
}
