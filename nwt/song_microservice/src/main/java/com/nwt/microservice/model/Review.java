package com.nwt.microservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Column(name = "comment")
    @Size(min = 1, max = 500, message
            = "Komentar moze imati minimalno 1 karaktera, a maksimalno 500")
    private String comment;

    @Column(name = "rating")
    @Min(value = 1, message = "Ocjena mora biti veca ili jednaka od 1")
    @Max(value = 5, message = "Ocjena mora biti manja ili jednaka od 5")
    private Integer rating;

    @Column(name = "dateComment")
    private Date dateComment;

    public Review() {}

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
