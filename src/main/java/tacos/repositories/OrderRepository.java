package tacos.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entities.Order;
import tacos.entities.User;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByZip(String zip);

    List<Order> findAllByZipAndPlacedAtBetween(String zip, Date startDate, Date andDate);
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    //@Query("SELECT  Order from Order o where o.zip=?1 and o.placedAt between ?2 and ?3")
    //List<Order> getOrders(String zip, Date startDate, Date andDate);
}
