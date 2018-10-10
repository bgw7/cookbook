package com.bgw.cookbook.domain.chef;

import com.bgw.cookbook.domain.recipe.Recipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chef implements Serializable {
    private static final long serialVersionUID = -7538629337269334210L;

    private Long id;
    private String name;
    private List<ChefIngredient> chefIngredients = new ArrayList<>();
    private List<Recipe> recipes = new ArrayList<>();

    public Chef() {
    }

    public Chef(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<ChefIngredient> getChefIngredients() {
        return chefIngredients;
    }

    public void setChefIngredients(List<ChefIngredient> chefIngredients) {
        this.chefIngredients = chefIngredients;
    }

    public void addChefIngredients(ChefIngredient chefIngredient) { this.chefIngredients.add(chefIngredient); }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
