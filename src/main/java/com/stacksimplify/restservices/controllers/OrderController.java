package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent()){
            throw new UserNotFoundException("User NOT FOUND");
        }

        return  userOptional.get().getOrder();
    }


    @PostMapping("/{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent()){
            throw new UserNotFoundException("User NOT FOUND");
        }

        User user = userOptional.get();
        order.setUser(user);

        return orderService.createOrder(order);
    }

    @GetMapping("/{userid}/orders/{orderid}")
    public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid){
        try {
            Optional<Order> optionalOrder = orderService.getOrderById(userid, orderid);
            return optionalOrder.get();
        }
        catch (OrderNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
