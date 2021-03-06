package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import org.hibernate.EntityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hateoas/users")
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public CollectionModel<User> getAllUsers() throws UserNotFoundException {

        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers){
            //Self link
            Long userid = user.getId();
            Link seltflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
            user.add(seltflink);

            // RelationShip Order

            CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
            Link orderLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
            user.add(orderLink);
        }

        // Selft link for getAllUsers
        Link selflinkAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();

        CollectionModel<User> finalResource = CollectionModel.of(allUsers, selflinkAllUsers);

        return finalResource;
    }

    @GetMapping("/{id}")
    public EntityModel<User>  getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            Optional<User> userOptional = userService.getUserById(id);
            User user = userOptional.get();
            Long userid = user.getId();

            Link seltflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();

            user.add(seltflink);
            EntityModel<User> finalResource = EntityModel.of(user);
            return finalResource;

        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
