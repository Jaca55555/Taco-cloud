package tacos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import tacos.constants.IngredientType;

@Data
@AllArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final IngredientType type;


}
