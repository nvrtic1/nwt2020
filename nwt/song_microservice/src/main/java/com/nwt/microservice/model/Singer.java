package com.nwt.microservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

@Data

@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;
    private String lastname;
    private Date birthday;
    private String biography;


    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private Set<SongSinger> songSingers = new HashSet<>();

    public Singer(){}
    public Singer(String firstname, String lastname, Date birthday, String biography)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.biography = biography;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }
}