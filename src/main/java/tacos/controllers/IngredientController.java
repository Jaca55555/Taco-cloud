package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.entities.Order;
import tacos.repositories.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/{id}")
public class IngredientController {
//    @Autowired
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/current")
    public String findById(PathVariable id,Model model) {


        model.addAttribute("order", new Order());
        return "orderForm";
    }


}
