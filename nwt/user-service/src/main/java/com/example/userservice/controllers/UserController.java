package com.example.userservice.controllers;

import com.example.userservice.exception.ResourceNotFoundException;
import com.example.userservice.models.UserModel;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        var users = userService.getAllUsers();
        if(users.size() == 0)
            throw  new ResourceNotFoundException("There is no users!");
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<UserModel> getUserById(@PathVariable Integer id){
        var user = userService.getUser(id);
        if (user.isPresent() == false) {
            throw  new ResourceNotFoundException("There is no user whit ID: "+id);
        }
        return user;
    }

    @PostMapping("/users")
    public UserModel newUser(@Valid @RequestBody UserModel user) {
        return userService.addUser(user);
    }

    @PutMapping("/users")
    public UserModel updateSong(@Valid @RequestBody UserModel newUser) {
        return userService.editUser(newUser);
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            throw ex;
        }
    }
}
