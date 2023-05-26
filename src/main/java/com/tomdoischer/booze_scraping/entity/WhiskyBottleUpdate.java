package com.tomdoischer.booze_scraping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class WhiskyBottleUpdate {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private double price;
    private String link;
    private boolean inStock;

    @ManyToOne
    @JoinColumn(name = "whisky_id", nullable = true) // temporary
    private WhiskyBottle whiskyBottle;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public WhiskyBottle getWhiskyBottle() {
        return whiskyBottle;
    }

    public void setWhiskyBottle(WhiskyBottle whiskyBottle) {
        this.whiskyBottle = whiskyBottle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
