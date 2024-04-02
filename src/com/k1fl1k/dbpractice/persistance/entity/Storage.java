package com.k1fl1k.dbpractice.persistance.entity;

import java.util.Objects;
import java.util.UUID;

public class Storage {

    private UUID id;
    private String name;
    private String street;
    private int sections;
    private int searchId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Storage storage = (Storage) o;
        return sections == storage.sections && searchId == storage.searchId && Objects.equals(
            id, storage.id) && Objects.equals(name, storage.name) && Objects.equals(
            street, storage.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, sections, searchId);
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public Storage(UUID id, String name, String street, int sections, int searchId) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.sections = sections;
        this.searchId = searchId;
    }

    public Storage() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getSections() {
        return sections;
    }

    public void setSections(int sections) {
        this.sections = sections;
    }
}
