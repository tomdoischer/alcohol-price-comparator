package com.tomdoischer.booze_scraping.service;

import com.tomdoischer.booze_scraping.dto.WhiskyBottleUpdateDto;
import com.tomdoischer.booze_scraping.entity.WhiskyBottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;
import com.tomdoischer.booze_scraping.repository.WhiskyBottleUpdateRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Tomáš Doischer
 */
@Service
public class WhiskyBottleUpdateService {

	private final WhiskyBottleUpdateRepository whiskyBottleUpdateRepository;

	@Autowired
	public WhiskyBottleUpdateService(WhiskyBottleUpdateRepository whiskyBottleUpdateRepository) {
		this.whiskyBottleUpdateRepository = whiskyBottleUpdateRepository;
	}

	public WhiskyBottleUpdate save(WhiskyBottleUpdate whiskyBottleUpdate) {
		if (whiskyBottleUpdate.getWhiskyBottle() == null) {
			throw new IllegalArgumentException("WhiskyBottleUpdate must have a WhiskyBottle");
		}

		whiskyBottleUpdate.setTimestamp(ZonedDateTime.now());

		return whiskyBottleUpdateRepository.save(whiskyBottleUpdate);
	}

	public List<WhiskyBottleUpdate> findByWhiskyBottle(WhiskyBottle whiskyBottle) {
		return whiskyBottleUpdateRepository.findByNameIgnoreCase(whiskyBottle.getName());
	}

	public WhiskyBottleUpdateDto toDto(WhiskyBottleUpdate update, List<WhiskyBottleUpdate> otherUpdates) {
		return new WhiskyBottleUpdateDto(update.getId(),
				update.getName(),
				update.getPrice(),
				update.getLink(),
				update.isInStock(),
				update.getTimestamp(),
				update.getStore(),
				isLatestUpdate(update, otherUpdates),
				compareToAveragePrice(update));
	}

	private double compareToAveragePrice(WhiskyBottleUpdate update) {
		// TODO
		return 0;
	}

	private boolean isLatestUpdate(WhiskyBottleUpdate update, List<WhiskyBottleUpdate> otherUpdates) {
		// TODO
		return false;
	}
}
