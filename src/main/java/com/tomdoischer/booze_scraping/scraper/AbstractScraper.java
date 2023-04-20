package com.tomdoischer.booze_scraping.scraper;

import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;
import com.tomdoischer.booze_scraping.service.WhiskyBottleUpdateService;

/**
 * @author Tomáš Doischer
 */
public abstract class AbstractScraper {

	private final WhiskyBottleUpdateService whiskyBottleUpdateService;

	protected AbstractScraper(WhiskyBottleUpdateService whiskyBottleUpdateService) {
		this.whiskyBottleUpdateService = whiskyBottleUpdateService;
	}

	protected WhiskyBottleUpdate createWhiskyBottleUpdate(double price, boolean availability,
														String name, String link) {
		WhiskyBottleUpdate whiskyBottleUpdate = new WhiskyBottleUpdate();
		whiskyBottleUpdate.setName(name);
		whiskyBottleUpdate.setPrice(price);
		whiskyBottleUpdate.setLink(link);
		whiskyBottleUpdate.setInStock(availability);
		whiskyBottleUpdateService.save(whiskyBottleUpdate);
		return whiskyBottleUpdate;
	}
}
