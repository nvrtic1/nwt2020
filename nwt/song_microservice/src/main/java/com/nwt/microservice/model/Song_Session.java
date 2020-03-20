package com.nwt.microservice.model;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
public class Song_Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    //@JoinColumn
    //@MapsId
    private Song song;

    public Song_Session() {}

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
