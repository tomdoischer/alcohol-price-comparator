package com.tomdoischer.booze_scraping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;

/**
 * @author Tomáš Doischer
 */
@Entity
public class WhiskyBottle {

	@Id
	private long id;
	@Column(name = "name", unique = true)
	private String name;

}
