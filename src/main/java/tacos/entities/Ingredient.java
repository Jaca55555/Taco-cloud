package tacos.entities;

import lombok.*;
import tacos.constants.IngredientType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient implements Serializable {
    @Id
    private  String id;
    private  String name;

    @Enumerated(EnumType.STRING)
    private  IngredientType type;


}
