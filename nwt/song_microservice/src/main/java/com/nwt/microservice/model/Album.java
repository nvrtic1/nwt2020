package com.nwt.microservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

@Data

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private Date dateRelease;


    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private Set<SongAlbum> songAlbums = new HashSet<>();

    public Album(){}
    public Album (String title, String description, Date dateRelease)
    {
        this.title = title;
        this.description = description;
        this.dateRelease = dateRelease;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}