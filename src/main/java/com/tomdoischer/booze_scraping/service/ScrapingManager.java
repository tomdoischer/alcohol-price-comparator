package com.tomdoischer.booze_scraping.service;

import com.tomdoischer.booze_scraping.scraper.AbstractScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScrapingManager {

    private final List<AbstractScraper> scrapers;

    public ScrapingManager(List<AbstractScraper> scrapers) {
        this.scrapers = scrapers;
    }

    public void scrapeAll() {
        for (AbstractScraper scraper : scrapers) {
            try {
                scraper.scrape();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
