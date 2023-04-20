package com.tomdoischer.booze_scraping.service;

import org.springframework.stereotype.Service;

import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;
import com.tomdoischer.booze_scraping.repository.WhiskyBottleUpdateRepository;

/**
 * @author Tomáš Doischer
 */
@Service
public class WhiskyBottleUpdateService {

	private final WhiskyBottleUpdateRepository whiskyBottleUpdateRepository;
	private final WhiskyBottleService whiskyBottleService;

	public WhiskyBottleUpdateService(WhiskyBottleUpdateRepository whiskyBottleUpdateRepository, WhiskyBottleService whiskyBottleService) {
		this.whiskyBottleUpdateRepository = whiskyBottleUpdateRepository;
		this.whiskyBottleService = whiskyBottleService;
	}

	public WhiskyBottleUpdate save(WhiskyBottleUpdate whiskyBottleUpdate) {
		if (whiskyBottleUpdate.getWhiskyBottle() == null) {
			whiskyBottleService.findWhiskyBottle(whiskyBottleUpdate.getName())
				.ifPresent(whiskyBottleUpdate::setWhiskyBottle);
		}

		return whiskyBottleUpdateRepository.save(whiskyBottleUpdate);
	}
}
