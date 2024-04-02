package com.k1fl1k.dbpractice.persistance.service.validation;


import java.util.ArrayList;
import java.util.List;

public class Validation {
    private String type;
    private String name;
    private String inside;
    private String street;
    private String surname;
    private String post;
    private int sections;
    private int value;
    private int price;
    private int searchId;
    private final List<String> errors;

    public Validation(){
        this.errors = new ArrayList<>();
    }

    public Validation type(String type){
        this.type = type;
        return this;
    }

    public Validation name(String name){
        this.name = name;
        return this;
    }
    public Validation inside(String inside){
        this.inside = inside;
        return this;
    }
    public Validation street(String street){
        this.street = street;
        return this;
    }
    public Validation surname(String surname){
        this.surname = surname;
        return this;
    }
    public Validation post(String post){
        this.post = post;
        return this;
    }

    public Validation sections(int sections){
        this.sections = sections;
        return this;
    }

    public Validation value(int value){
        this.value = value;
        return this;
    }
    public Validation price(int price){
        this.price = price;
        return this;
    }

    public Validation searchId(int searchId){
        this.searchId = searchId;
        return this;
    }

    public List<String> validate(){
        errors.clear();

        validateType();
        validateInside();
        validateName();
        validateValue();
        validatePrice();
        validateSearchId();
        validatePost();
        validateStreet();
        validateSurname();
        validateSections();

        return errors;
    }

    private void validateType() {
        if (type == null || type.trim().isEmpty()) {
            errors.add("Type cannot be empty");
        }
    }

    private void validateName() {
        if (name == null || name.trim().isEmpty()) {
            errors.add("Name cannot be empty");
        }
    }

    private void validateInside() {
        if (inside == null || inside.trim().isEmpty()) {
            errors.add("Inside cannot be empty");
        }
    }

    private void validateStreet() {
        if (street == null || street.trim().isEmpty()) {
            errors.add("Street cannot be empty");
        }
    }

    private void validateSurname() {
        if (surname == null || surname.trim().isEmpty()) {
            errors.add("Surname cannot be empty");
        }
    }

    private void validatePost() {
        if (post == null || post.trim().isEmpty()) {
            errors.add("Post cannot be empty");
        }
    }

    private void validateSections() {
        if (sections <= 0) {
            errors.add("Sections must be a positive integer");
        }
    }

    private void validateValue() {
        if (value < 0) {
            errors.add("Value must be 0 or more");
        }
    }

    private void validatePrice() {
        if (price <= 0) {
            errors.add("Price must be a positive number");
        }
    }

    private void validateSearchId() {
        if (searchId <= 0) {
            errors.add("Search ID must be a positive number");
        }
    }
}

