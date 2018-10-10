package com.bgw.cookbook.adapter.service;

import com.bgw.cookbook.adapter.datastore.JdbcChefRepository;
import com.bgw.cookbook.domain.chef.Chef;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChefDataServiceTest {


    @InjectMocks
    private ChefDataService dataService;

    @Mock
    private JdbcChefRepository repository;

    @Test
    public void findById_success() {
        when(repository.findById(anyLong())).thenReturn(new Chef());
        Chef actualChef = dataService.findById(1L);

        assertThat(actualChef, isA(Chef.class));
    }

}
