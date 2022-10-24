package tacos.repositories.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tacos.constants.IngredientType;
import tacos.models.Ingredient;
import tacos.repositories.IngredientRepository;

import java.sql.*;
import java.util.List;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private JdbcTemplate jdbc;



    @Override
    public Ingredient findById(String id) {

        return jdbc.queryForObject("select id, name, type from Ingredient where id=?",
                (rs, rowNum) -> new Ingredient(rs.getString("id"),
                        rs.getString("name"),
                        IngredientType.valueOf(rs.getString("type"))), id);
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
            throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                IngredientType.valueOf(rs.getString("type")));
    }




    @Override
    public List<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {

        jdbc.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}
