package com.tomdoischer.booze_scraping.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tomdoischer.booze_scraping.dto.WhiskyBottleDto;
import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;
import org.springframework.stereotype.Service;

import com.tomdoischer.booze_scraping.entity.WhiskyBottle;
import com.tomdoischer.booze_scraping.repository.WhiskyBottleRepository;

/**
 * @author Tomáš Doischer
 */
@Service
public class WhiskyBottleService {

	private final WhiskyBottleRepository whiskyBottleRepository;
	private final WhiskyBottleUpdateService whiskyBottleUpdateService;

	public WhiskyBottleService(WhiskyBottleRepository whiskyBottleRepository, WhiskyBottleUpdateService whiskyBottleUpdateService) {
		this.whiskyBottleRepository = whiskyBottleRepository;
		this.whiskyBottleUpdateService = whiskyBottleUpdateService;
	}

	public Optional<WhiskyBottle> findWhiskyBottle(String name) {
		return whiskyBottleRepository.findByNameIgnoreCase(name);
	}

	public WhiskyBottle save(WhiskyBottle whiskyBottle) {
		return whiskyBottleRepository.save(whiskyBottle);
	}

	public List<WhiskyBottle> findAll() {
		return (List<WhiskyBottle>) whiskyBottleRepository.findAll();
	}

	public List<WhiskyBottleDto> findAllDto() {
		return this.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public WhiskyBottleDto toDto(WhiskyBottle bottle) {
		return new WhiskyBottleDto(bottle.getId(), bottle.getName(), bottle.getAveragePriceAll(), bottle.getAveragePricePast30Days(), whiskyBottleUpdateService.findByWhiskyBottle(bottle));
	}
}
