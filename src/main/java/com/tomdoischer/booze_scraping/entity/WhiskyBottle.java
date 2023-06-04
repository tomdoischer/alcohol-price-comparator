package com.tomdoischer.booze_scraping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * @author Tomáš Doischer
 */
@Entity
@Table(name = "whisky_bottle", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class WhiskyBottle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", unique = true)
	private String name;
	@Column(name = "average_price_all")
	private double averagePriceAll;
	@Column(name = "average_price_past_30_days")
	private double averagePricePast30Days;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAveragePriceAll() {
		return averagePriceAll;
	}

	public void setAveragePriceAll(double averagePriceAll) {
		this.averagePriceAll = averagePriceAll;
	}

	public double getAveragePricePast30Days() {
		return averagePricePast30Days;
	}

	public void setAveragePricePast30Days(double averagePricePast30Days) {
		this.averagePricePast30Days = averagePricePast30Days;
	}
}
