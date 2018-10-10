package com.bgw.cookbook.adapter.datastore;

import com.bgw.cookbook.domain.chef.Chef;
import com.bgw.cookbook.domain.chef.ChefIngredient;
import com.bgw.cookbook.domain.chef.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcChefRepository implements ChefRepository {

    private static final String SQL_Select_Chef_By_Id = "SELECT chef.id, chef.name, ci.id as ingredient_id, ci.added, ci.name as ingredient_name, ci.chef_id, ci.quantity, ci.state " +
            "FROM Chef chef left join Chef_Ingredient ci on ci.chef_id = chef.id " +
            "WHERE chef.id = :id";

    private static final String SQL_UPDATE_Chef_By_Id = "UPDATE chef set name = :name WHERE id = :id";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Chef findById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.query(SQL_Select_Chef_By_Id, namedParameters, new ChefMapper());
    }

    public void updateChef(Chef chef) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", chef.getId());
        namedParameters.addValue("name", chef.getName());
        jdbcTemplate.update(SQL_UPDATE_Chef_By_Id, namedParameters);
    }

    private static final class ChefMapper implements ResultSetExtractor<Chef> {
        public Chef extractData(ResultSet rs) throws SQLException {

            Chef chef = new Chef();
            ChefIngredient ingredient = new ChefIngredient();
            while(rs.next()) {
                if (chef.getName() == null) {
                    chef.setId(rs.getLong("id"));
                    chef.setName(rs.getString("name"));
                }
                ingredient.setId(rs.getLong("ingredient_id"));
                ingredient.setAdded(rs.getDate("added"));
                ingredient.setChefId(rs.getLong("chef_id"));
                ingredient.setName(rs.getString("ingredient_name"));
                ingredient.setQuantity(rs.getLong("quantity"));
                ingredient.setState(rs.getString("state"));
                chef.addChefIngredients(ingredient);
            }
            return chef;
        }
    }
}
