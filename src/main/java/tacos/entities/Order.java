package tacos.entities;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private long id;

    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="Street is required")
    private String street;
    @NotBlank(message="City is required")
    private String city;
    @NotBlank(message="State is required")
    private String state;
    @NotBlank(message="Zip code is required")
    private String zip;
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @ManyToOne
    private User user;

    private Date placedAt;
    private Date createdAt;
    private Date updatedAt;

    @ManyToMany(targetEntity=Ingredient.class)

    private List<Taco> tacos=new ArrayList<>();
    public void addTaco(Taco saved) {
        tacos.add(saved);
    }

    public List<Taco> getTacos() {
        return null;
    }
    @PrePersist
    void createdAt() {
        this.updatedAt = new Date();
        this.placedAt = new Date();
        this.createdAt = new Date();
    }
    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }
}
