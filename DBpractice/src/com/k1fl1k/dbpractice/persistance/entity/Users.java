package com.k1fl1k.dbpractice.persistance.entity;

import java.util.Objects;
import java.util.UUID;

public class Users {

    private UUID id;
    private String name;
    private String surname;
    private String post;
    private  int searchId;

    public Users(UUID id, String name, String surname, String post, int searchId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.post = post;
        this.searchId = searchId;
    }

    public Users(){
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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
        Users users = (Users) o;
        return searchId == users.searchId && Objects.equals(id, users.id)
            && Objects.equals(name, users.name) && Objects.equals(surname,
            users.surname) && Objects.equals(post, users.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, post, searchId);
    }
}
