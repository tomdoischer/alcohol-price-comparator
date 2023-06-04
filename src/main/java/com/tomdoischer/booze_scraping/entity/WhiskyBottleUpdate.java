package com.tomdoischer.booze_scraping.entity;

import com.tomdoischer.booze_scraping.stores.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;

@Entity
@Table(name = "whisky_bottle_update")
public class WhiskyBottleUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String link;
    private boolean inStock;
    private ZonedDateTime timestamp;
    private Store store;

    @ManyToOne
    @JoinColumn(name = "whisky_bottle_id", nullable = false)
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

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
