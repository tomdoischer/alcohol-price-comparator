package com.tomdoischer.booze_scraping.scraper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class GWScraperTest {

    @Autowired
    private GWScraper scraper;

    @Test
    public void testScraper() {
        try {
            scraper.scrape();
        } catch (IOException e) {

        }

    }
}
