package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public Optional<Order> getOrderById(Long userid, Long orderid) throws OrderNotFoundException {

        Optional<Order> orderOptional = orderRepository.findById(orderid);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            if (order.getUser().getId() == userid) {
                return orderRepository.findById(orderid);
            }
        }

        throw new OrderNotFoundException("Order not found in user Repository");
    }
}
