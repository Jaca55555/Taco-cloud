package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import tacos.entities.Taco;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {

        SpringApplication.run(TacoCloudApplication.class, args);}



}
