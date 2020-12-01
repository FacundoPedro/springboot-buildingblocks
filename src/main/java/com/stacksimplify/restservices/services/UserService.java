package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //Autowired user repository
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    public User createUser(User user) throws UserExistsException {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser != null){
            throw new UserExistsException("User already exists in repository");
        }

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id);
        throw new UserNotFoundException("User not found in user Repository");
    }

    public User updateUserById(User user, Long id) throws UserNotFoundException {

        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        }else{
            throw new UserNotFoundException("User not found in user Repository, provide de correct user id");
        }
    }

    public void deleteUserById(Long id){
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
        else{

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in user Repository, provide de correct user id");
        }
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
