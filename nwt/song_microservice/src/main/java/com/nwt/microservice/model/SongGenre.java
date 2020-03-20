package com.nwt.microservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class SongGenre implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private Song song;

    @Id
    @ManyToOne
    @JoinColumn
    private Genre genre;

    private Date genreDate;

    public SongGenre(Genre genre, Date genreDate) {
        this.genre = genre;
        this.genreDate = genreDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongGenre)) return false;
        SongGenre that = (SongGenre) o;
        return Objects.equals(song.getName(), that.song.getName()) &&
                Objects.equals(genre.getName(), that.genre.getName()) &&
                Objects.equals(genreDate, that.genreDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(song.getName(), genre.getName(), genreDate);
    }

    public void setSong(Song song) {
        this.song = song;
    }
}