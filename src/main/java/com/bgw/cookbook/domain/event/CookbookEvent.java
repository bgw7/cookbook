package com.bgw.cookbook.domain.event;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CookbookEvent<O> implements Event, Serializable {
    private static final long serialVersionUID = -5729654345759516851L;

    @JsonIgnore
    private Long chefId;

    @JsonIgnore
    private List<Long> recipeIds;

    @JsonIgnore
    private List<Long> ingredientIds;

    @JsonIgnore
    private boolean global = false;

    @JsonIgnore
    private transient O cookbookDomainObject;

    public CookbookEvent(O cookbookDomainObject) {
        this.cookbookDomainObject = cookbookDomainObject;
    }

    @JsonAnyGetter
    public Map<String, Object> any() {
        return Collections.singletonMap(cookbookDomainObject.getClass().getSimpleName(), cookbookDomainObject);
    }

    public O getCookbookDomainObject() {
        return cookbookDomainObject;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Long getChefId() {
        return chefId;
    }

    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public List<Long> getRecipeIds() {
        return recipeIds;
    }

    public List<Long> getIngredientIds() {
        return ingredientIds;
    }

    public void setRecipeIds(List<Long> recipeIds) {
        this.recipeIds = recipeIds;
    }

    public void setIngredientIds(List<Long> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }
}
