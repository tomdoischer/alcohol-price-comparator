package com.tomdoischer.booze_scraping.scraper;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ScraperTest {
    @Test
    public void testAlfredScrape() {
        Scraper scraper = new Scraper();
        try {
            scraper.scrape();
        } catch (IOException e) {

        }

    }
}
