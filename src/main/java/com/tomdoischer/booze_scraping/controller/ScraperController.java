package com.tomdoischer.booze_scraping.controller;

import com.tomdoischer.booze_scraping.service.ScrapingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scraper")
public class ScraperController {

    private final ScrapingManager scrapingManager;

    @Autowired
    public ScraperController(ScrapingManager scrapingManager) {
        this.scrapingManager = scrapingManager;
    }

    @PostMapping("/scrape-all")
    public void scrapeAll() {
        scrapingManager.scrapeAll();
    }
}
