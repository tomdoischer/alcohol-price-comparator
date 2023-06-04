package com.tomdoischer.booze_scraping.dto;

import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;

import java.time.ZonedDateTime;
import java.util.List;

public record WhiskyBottleDto(Long id, String name, double averagePriceAll, double averagePricePast30Days, List<WhiskyBottleUpdate> updates) {

}
