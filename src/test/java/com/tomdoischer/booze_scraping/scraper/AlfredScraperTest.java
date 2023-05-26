package com.tomdoischer.booze_scraping.scraper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class AlfredScraperTest {

    @Autowired
    private AlfredScraper alfredScraper;

    @Test
    public void testAlfredScrape() {
        try {
            alfredScraper.scrape();
        } catch (IOException e) {

        }

    }
}
