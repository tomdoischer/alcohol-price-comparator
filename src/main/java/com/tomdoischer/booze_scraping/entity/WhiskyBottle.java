package com.tomdoischer.booze_scraping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;

/**
 * @author Tomáš Doischer
 */
@Entity
public class WhiskyBottle {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "name", unique = true)
	private String name;

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
}
