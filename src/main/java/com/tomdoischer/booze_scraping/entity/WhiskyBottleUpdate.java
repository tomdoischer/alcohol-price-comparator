package com.tomdoischer.booze_scraping.entity;

public class WhiskyBottleUpdate {

    private long id;
    private String name;
    private double price;
    private String link;
    private boolean inStock;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WhiskyBottle getWhiskyBottle() {
        return whiskyBottle;
    }

    public void setWhiskyBottle(WhiskyBottle whiskyBottle) {
        this.whiskyBottle = whiskyBottle;
    }
}
