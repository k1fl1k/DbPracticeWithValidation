package com.k1fl1k.dbpractice.persistance.entity;

import java.util.Objects;
import java.util.UUID;

public class Sections {

    private UUID id;
    private String name;
    private String inside;
    private int value;
    private int searchId;

    public Sections(UUID id, String name, String inside, int value, int searchId) {
        this.id = id;
        this.name = name;
        this.inside = inside;
        this.value = value;
        this.searchId = searchId;
    }

    public Sections(){
        this.id = UUID.randomUUID();
    }
    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
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

    public String getInside() {
        return inside;
    }

    public void setInside(String inside) {
        this.inside = inside;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sections sections = (Sections) o;
        return value == sections.value && searchId == sections.searchId && Objects.equals(id,
            sections.id) && Objects.equals(name, sections.name) && Objects.equals(
            inside, sections.inside);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, inside, value, searchId);
    }
}
