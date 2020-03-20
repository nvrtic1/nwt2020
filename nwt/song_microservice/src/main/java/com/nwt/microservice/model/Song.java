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
    private Set<SongSinger> songSingers;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SongGenre> songGenres;

    @OneToOne(mappedBy = "song", cascade = CascadeType.ALL)
    @JsonIgnore
    private Song_Session songSession;

    public Song(){}
    public Song(String name, String description, Double rating, Integer length, Song_Session songSession, ArrayList<SongSinger> songSingers, ArrayList<SongGenre> songGenres) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.length = length;

        this.songSession = songSession;
        this.songSession.setSong(this);
        for(SongSinger songSinger : songSingers) songSinger.setSong(this);
        this.songSingers = new HashSet<SongSinger>(songSingers);
        for(SongGenre songGenre : songGenres) songGenre.setSong(this);
        this.songGenres = new HashSet<SongGenre>(songGenres);
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