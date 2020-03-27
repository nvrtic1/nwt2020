package com.nwt.microservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    //@JoinColumn
    //@MapsId
    private Song song;

    private String comment;
    private Integer rating;
    private Date dateComment;

    public Review() {}

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
