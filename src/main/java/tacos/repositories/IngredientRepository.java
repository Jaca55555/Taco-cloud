package tacos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entities.Ingredient;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, String > {

}

