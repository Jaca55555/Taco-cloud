package tacos.controllers.api;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.entities.Order;
import tacos.entities.Taco;
import tacos.repositories.OrderRepository;
import tacos.repositories.TacoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders", produces = {"application/json", "text/xml"})
@CrossOrigin(origins = "*")
public class ApiOrderController {
    private final OrderRepository orderRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {
        Order order = orderRepository.findById(orderId).get();
        if (patch.getName() != null) {
            order.setName(patch.getName());
        }
        if (patch.getStreet() != null) {
            order.setStreet(patch.getStreet());
        }
        if (patch.getCity() != null) {
            order.setCity(patch.getCity());
        }
        if (patch.getState() != null) {
            order.setState(patch.getState());
        }
        if (patch.getZip() != null) {
            order.setZip(patch.getState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }


}
