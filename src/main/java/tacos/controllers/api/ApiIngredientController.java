package tacos.controllers.api;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.entities.Ingredient;
import tacos.entities.Order;
import tacos.entities.Taco;
import tacos.repositories.IngredientRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ingredients", produces = {"application/json", "text/xml"})
@CrossOrigin(origins = "*")
public class ApiIngredientController {
    private final IngredientRepository ingredientRepository;

    @GetMapping
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable String  id) {
        Optional<Ingredient> optional = ingredientRepository.findById(id);
        return optional.map(ingredient -> new ResponseEntity<>(ingredient, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }


}
