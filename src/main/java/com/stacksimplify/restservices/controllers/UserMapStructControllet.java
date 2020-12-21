package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.mappers.UserMapper;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructControllet {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserMsDto> getAllUsersDtos(){
        return userMapper.usersToUserDtos(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMsDto getUserDtoById(@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return userMapper.userToUserMsDto(user);


    }
}
