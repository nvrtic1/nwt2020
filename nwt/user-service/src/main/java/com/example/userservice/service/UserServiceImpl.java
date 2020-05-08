package com.example.userservice.service;

import com.netflix.discovery.converters.Auto;
import com.example.userservice.exception.ResourceNotFoundException;
import com.example.userservice.models.UserModel;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Optional<UserModel> getUser(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Boolean deleteAllUsers() {
        userRepository.deleteAll();
        return true;
    }

    @Override
    public UserModel editUser(UserModel user) {
        return userRepository.findById(user.getId()).map(newUser -> {
            newUser.setName(user.getName());
            newUser.setLocation(user.getLocation());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setEmail(user.getEmail());
            userRepository.save(newUser);
            return newUser;
        }).orElseThrow(() -> new ResourceNotFoundException("User does not exist!"));
    }

    @Override
    public UserModel deleteUserById(Integer id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return user;
        }).orElseThrow(() -> new ResourceNotFoundException("User does not exist!"));
    }

    @Override
    public UserModel addUser(UserModel user) {
        userRepository.save(user);
        return user;
    }
}
