package tacos.repositories;

import tacos.models.Ingredient;

import java.util.List;

public interface IngredientRepository {
     Ingredient findById(String id);
     List<Ingredient> findAll();
     Ingredient save(Ingredient ingredient);
}
