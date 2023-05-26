package com.tomdoischer.booze_scraping.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tomdoischer.booze_scraping.entity.WhiskyBottle;
import com.tomdoischer.booze_scraping.repository.WhiskyBottleRepository;

/**
 * @author Tomáš Doischer
 */
@Service
public class WhiskyBottleService {

	private final WhiskyBottleRepository whiskyBottleRepository;

	public WhiskyBottleService(WhiskyBottleRepository whiskyBottleRepository) {
		this.whiskyBottleRepository = whiskyBottleRepository;
	}

	public Optional<WhiskyBottle> findWhiskyBottle(String name) {
		return whiskyBottleRepository.findByNameIgnoreCase(name);
	}

	public WhiskyBottle save(WhiskyBottle whiskyBottle) {
		return whiskyBottleRepository.save(whiskyBottle);
	}
}
