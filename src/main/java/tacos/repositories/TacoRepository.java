package tacos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entities.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
