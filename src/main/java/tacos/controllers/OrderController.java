package tacos.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.entities.Order;
import tacos.entities.User;
import tacos.property.holders.OrderProps;
import tacos.repositories.OrderRepository;
import tacos.util.SecurityContextUtil;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
@SessionAttributes("order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderProps props;



    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        log.info("Order submitted: " + order);
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(SecurityContextUtil.getUser());

        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());


        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }



}

