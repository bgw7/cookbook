package com.bgw.cookbook.domain.recipe;

import com.bgw.cookbook.domain.event.Event;
import com.bgw.cookbook.domain.ingredient.Ingredient;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Event, Serializable {
    private static final long serialVersionUID = -992254638962279830L;

    private Long id;
    private Long chefId;
    private List<Ingredient> ingredients;

    public Recipe(Long id, Long chefId, List<Ingredient> ingredients) {
        this.id = id;
        this.chefId = chefId;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChefId() {
        return chefId;
    }

    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
