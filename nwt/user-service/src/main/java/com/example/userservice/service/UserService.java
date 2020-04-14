package com.example.userservice.service;

import com.example.userservice.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface UserService {
    public ArrayList<UserModel> getAllUsers();
    public Optional<UserModel> getUser(Integer id);
    public Boolean deleteAllUsers();
    public UserModel editUser(UserModel user);
    public UserModel deleteUserById(Integer id);
    public UserModel addUser(UserModel user);
}
