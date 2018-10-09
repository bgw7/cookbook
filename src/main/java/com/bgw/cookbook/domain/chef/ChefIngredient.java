package com.bgw.cookbook.domain.chef;

import com.bgw.cookbook.domain.ingredient.Ingredient;

import java.io.Serializable;
import java.util.Date;

public class ChefIngredient extends Ingredient implements Serializable {

    private static final long serialVersionUID = 3858330325589157580L;
    private Long chefId;
    private Date added;

    public ChefIngredient() {
    }

    public ChefIngredient(Long id, String name, Long quantity, String state, Long chefId, Date added) {
        super(id, name, quantity, state);
        this.chefId = chefId;
        this.added = added;
    }

    public Long getChefId() {
        return chefId;
    }

    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }
}
