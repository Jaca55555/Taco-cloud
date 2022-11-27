package tacos.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.constants.IngredientType;
import tacos.entities.Ingredient;
import tacos.entities.Order;
import tacos.entities.Taco;
import tacos.repositories.IngredientRepository;
import tacos.repositories.TacoRepository;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@Slf4j
@AllArgsConstructor
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientRepository ingredientRepository;
   private TacoRepository tacoRepository;
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            System.out.println(type.toString().toLowerCase()+" => "+filterByType(ingredients, type));
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType()==type).collect(Collectors.toList());
    }

    @PostMapping
    public String processOrder(@Valid Taco taco, Errors errors,@ModelAttribute Order order, Model model) {
        log.info("taco: "+taco);
        if (errors.hasErrors()){

            return "design";

        }

        Taco saved=tacoRepository.save(taco);

        order.addTaco(saved);
        return "redirect:/orders/current";
   }

}
