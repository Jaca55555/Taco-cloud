package tacos.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.assemblers.TacoModelAssembler;
import tacos.controllers.api.ApiDesignTacoController;
import tacos.dto.TacoModel;
import tacos.entities.Taco;
import tacos.repositories.TacoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RepositoryRestController
@RequiredArgsConstructor
public class RestTacoController {
    private final TacoRepository tacoRepository;

    @GetMapping(path="/tacos/recent", produces="application/hal+json")
    public ResponseEntity< CollectionModel<TacoModel>> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page).getContent();
        CollectionModel<TacoModel> recentResources =
                new TacoModelAssembler().toCollectionModel(tacos);

        recentResources.add(WebMvcLinkBuilder.linkTo(ApiDesignTacoController.class)
                .slash("recent")
                .withRel("recents"));

        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }

}



