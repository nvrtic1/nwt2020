package com.nwt.microservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
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
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "length")
    private Integer length;
//moyda yato sto je ondje double ne trebaju navodnici
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