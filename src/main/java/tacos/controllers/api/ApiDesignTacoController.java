package tacos.controllers.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.assemblers.TacoModelAssembler;
import tacos.dto.TacoModel;
import tacos.entities.Taco;
import tacos.repositories.TacoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/design",produces ={"application/json", "text/xml"})
@CrossOrigin(origins = "*")
public class ApiDesignTacoController {
    private final TacoRepository tacoRepository;

    @GetMapping
    public List<Taco> getAllTacos() {

        List<Taco> tacoList = new ArrayList<>();
        tacoRepository.findAll().forEach(tacoList::add);
        return tacoList;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/recent")
    public  CollectionModel<TacoModel> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());

        List<Taco> tacos = tacoRepository.findAll(page).getContent();
        CollectionModel<TacoModel> recentResources = new TacoModelAssembler().toCollectionModel(tacos);
      recentResources.add(WebMvcLinkBuilder.linkTo(ApiDesignTacoController.class)
                      .slash("recent")
              .withRel("recents"));

        return recentResources;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }



}
