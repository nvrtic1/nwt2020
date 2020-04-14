package com.nwt.microservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data

@Entity
public class Song {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 30, message
            = "Ime pjesme moze imati minimalno 1 karaktera, a maksimalno 30")
    private String name;

    @Column(name = "description")
    @Size(max = 500, message
            = "Opis pjesme moze imati maksimalno 500 karaktera")
    private String description;

    @Column(name = "rating")
    @Min(value = 1, message = "Ocjena mora biti veca ili jednaka od 1")
    @Max(value = 5, message = "Ocjena mora biti manja ili jednaka od 5")
    private Double rating;

    @Column(name = "length")
    @Min(value = 1, message = "Dužina pjesme mora biti veca ili jednaka od 1")
    private Integer length;
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)

    @JsonIgnore
    private Set<SongAlbum> songAlbums;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SongGenre> songGenres;

    @OneToOne(mappedBy = "song", cascade = CascadeType.ALL)
    @JsonIgnore
    private Review review;

    public Song(){}
    public Song(String name, String description, Double rating, Integer length, Review review, ArrayList<SongAlbum> songAlbums, ArrayList<SongGenre> songGenres) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.length = length;

        this.review = review;
        this.review.setSong(this);
        for(SongAlbum songAlbum : songAlbums) songAlbum.setSong(this);
        this.songAlbums = new HashSet<SongAlbum>(songAlbums);
        for(SongGenre songGenre : songGenres) songGenre.setSong(this);
        this.songGenres = new HashSet<SongGenre>(songGenres);
    }

    public Integer getID() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}