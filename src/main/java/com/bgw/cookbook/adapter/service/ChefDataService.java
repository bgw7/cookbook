package com.bgw.cookbook.adapter.service;

import com.bgw.cookbook.domain.chef.Chef;
import com.bgw.cookbook.domain.chef.ChefRepository;
import com.bgw.cookbook.domain.chef.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChefDataService implements ChefService {

    @Autowired
    private ChefRepository repo;

    public Chef findById(Long id) {
        return repo.findById(id);
    }

    public void updateChef(Chef chef) {
        repo.updateChef(chef);
    }
}
