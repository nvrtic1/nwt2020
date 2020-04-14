package com.example.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

@Data

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 60, message
            = "Name can contain maximum 60 characters")
    private String name;

    @Column(name = "location")
    @NotNull
    @Size(max = 50, message
            = "Location can contain maximum 50 characters")
    private String location;

    @Column(name = "phone_number")
    @NotNull
    @Size(min = 1, max = 60, message
            = "")
    private String phoneNumber;

    @Column(name = "email")
    @NotNull
    @Size(min = 1, max = 60, message
            = "")
    private String email;

    public UserModel(){}

    public UserModel (String name, String location, String phoneNumber, String email)
    {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}