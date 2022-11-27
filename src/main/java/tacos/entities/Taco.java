package tacos.entities;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@Entity
@RestResource(rel = "tacos",path = "tacos")
public class Taco implements Serializable {
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")

    private String name;
    @Size(min=1, message="You must choose at least 1 ingredient")
    @ManyToMany(targetEntity=Ingredient.class)
    private List<Ingredient> ingredients;
    private Date createdAt;
    private Date updatedAt;
    @PrePersist
    void createdAt() {
        this.updatedAt = new Date();
        this.createdAt = new Date();
    }
    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

}
