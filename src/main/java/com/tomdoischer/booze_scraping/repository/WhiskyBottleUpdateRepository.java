package com.tomdoischer.booze_scraping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tomdoischer.booze_scraping.entity.WhiskyBottle;
import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;
import org.springframework.stereotype.Repository;

/**
 * @author Tomáš Doischer
 */

@Repository
public interface WhiskyBottleUpdateRepository extends CrudRepository<WhiskyBottleUpdate, Long> {
	List<WhiskyBottleUpdate> findByNameIgnoreCase(String name);
}
