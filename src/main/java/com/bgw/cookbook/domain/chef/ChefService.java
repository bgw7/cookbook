package com.bgw.cookbook.domain.chef;

public interface ChefService {

    Chef findById(Long id);

    void updateChef(Chef chef);
}
