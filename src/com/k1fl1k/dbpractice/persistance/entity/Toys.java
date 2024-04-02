package com.k1fl1k.dbpractice.persistance.entity;

import java.util.Objects;
import java.util.UUID;

public class Toys {

    private UUID id;
    private String type;
    private int value;
    private int price;
    private int searchId;

    public Toys(UUID id, String type, int value, int price, int searchId) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.price = price;
        this.searchId = searchId;
    }

    public Toys() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Toys toys = (Toys) o;
        return value == toys.value && price == toys.price && searchId == toys.searchId
            && Objects.equals(id, toys.id) && Objects.equals(type, toys.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, price, searchId);
    }
}
