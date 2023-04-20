package com.tomdoischer.booze_scraping.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.tomdoischer.booze_scraping.entity.WhiskyBottle;
import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;

/**
 * @author Tomáš Doischer
 */

public interface WhiskyBottleUpdateRepository extends Repository<WhiskyBottleUpdate, Long> {
	Optional<WhiskyBottleUpdate> findByNameIgnoreCase(String name);

	WhiskyBottleUpdate save(WhiskyBottleUpdate whiskyBottleUpdate);
}
