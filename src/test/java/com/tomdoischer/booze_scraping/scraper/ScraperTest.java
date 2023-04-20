package com.tomdoischer.booze_scraping.scraper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;

import java.io.IOException;

public class ScraperTest {

    @Autowired
    private Scraper scraper;

    @Test
    public void testAlfredScrape() {
        try {
            scraper.scrape();
        } catch (IOException e) {

        }

    }
}
