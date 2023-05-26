package com.tomdoischer.booze_scraping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.tomdoischer.booze_scraping.entity.WhiskyBottle;
import org.springframework.stereotype.Repository;

/**
 * @author Tomáš Doischer
 */

@Repository
public interface WhiskyBottleRepository extends CrudRepository<WhiskyBottle, Long> {
	Optional<WhiskyBottle> findByNameIgnoreCase(String name);
}
