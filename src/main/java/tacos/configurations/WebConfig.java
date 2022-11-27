package tacos.configurations;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos.constants.IngredientType;
import tacos.entities.Ingredient;
import tacos.entities.Taco;
import tacos.repositories.IngredientRepository;
import tacos.repositories.UserRepository;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }

    @Bean
        public RepresentationModelProcessor<RepositoryLinksResource> tacoProcessor(EntityLinks links) {
            return model -> {
                model.add(
                        links.linkFor(Taco.class)
                                .slash("recent")
                                .withRel("recents"));
                return model;
            };
    }

    @Bean
    @Profile({"prod","local"})
    public CommandLineRunner dataLoader(IngredientRepository repo,  UserRepository userRepo, PasswordEncoder encoder) {

        return args -> {
            List<Ingredient> ingredients=List.of(

                    new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
                    new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
                    new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN)
                    );
            repo.saveAll(ingredients);
        };


    }



}

