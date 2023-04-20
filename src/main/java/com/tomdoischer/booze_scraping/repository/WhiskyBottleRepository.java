package com.tomdoischer.booze_scraping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.tomdoischer.booze_scraping.entity.WhiskyBottle;

/**
 * @author Tomáš Doischer
 */

public interface WhiskyBottleRepository extends Repository<WhiskyBottle, Long> {
	Optional<WhiskyBottle> findByNameIgnoreCase(String name);
}
