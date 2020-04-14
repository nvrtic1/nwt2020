package com.nwt.microservice.model;

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
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotNull
    @Size(min = 1, max = 30, message
            = "Ime albuma moze imati minimalno 1 karaktera, a maksimalno 30")
    private String title;

    @Column(name = "description")
    @Size(max = 500, message
            = "Opis albuma moze imati maksimalno 500 karaktera")
    private String description;

    @Column(name = "dateRelease")
    private Date dateRelease;


    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    @JsonIgnore

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    public Integer getId() {return id;}
}