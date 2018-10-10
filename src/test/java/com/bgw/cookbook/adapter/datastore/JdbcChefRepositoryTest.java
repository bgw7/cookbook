package com.bgw.cookbook.adapter.datastore;

import com.bgw.cookbook.domain.chef.Chef;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JdbcChefRepositoryTest {

    @InjectMocks
    private JdbcChefRepository repository;

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Test
    @SuppressWarnings("unchecked")
    public void getChefById() throws Exception {
        when(jdbcTemplate.query(anyString(), any(SqlParameterSource.class), any(ResultSetExtractor.class))).thenReturn(new Chef());
        Chef chef = repository.findById(1L);
        assertNotNull(chef);
    }
}
