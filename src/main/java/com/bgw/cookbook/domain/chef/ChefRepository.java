package com.bgw.cookbook.domain.chef;

public interface ChefRepository {

    Chef findById(Long id);

    void updateChef(Chef chef);
}
