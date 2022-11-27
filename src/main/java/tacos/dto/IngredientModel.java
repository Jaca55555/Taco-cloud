package tacos.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import tacos.constants.IngredientType;
import tacos.entities.Ingredient;

public class IngredientModel extends RepresentationModel<IngredientModel> {

    @Getter
    private final String name;
    @Getter
    private final IngredientType type;

    public IngredientModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

}
