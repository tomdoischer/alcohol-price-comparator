package com.tomdoischer.booze_scraping.dto;

import com.tomdoischer.booze_scraping.stores.Store;

import java.time.ZonedDateTime;

public record WhiskyBottleUpdateDto(Long id, String name, double price, String link, boolean inStock, ZonedDateTime timestamp, Store store, boolean isLatest, double compareToAverage) {
}
