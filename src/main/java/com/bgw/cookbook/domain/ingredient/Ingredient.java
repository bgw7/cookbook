package com.bgw.cookbook.domain.ingredient;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private static final long serialVersionUID = -1509306169732240308L;

    private Long id;
    private String name;
    private Long quantity;
    private String state;

    public Ingredient(Long id, String name, Long quantity, String state) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.state = state;
    }

    public Ingredient() {
    }

    public Ingredient(Long id) {
        this.id = id;
    }

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
