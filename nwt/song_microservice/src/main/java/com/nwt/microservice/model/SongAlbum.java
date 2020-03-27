package com.nwt.microservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class SongAlbum implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Album album;

    @Id
    @ManyToOne
    @JoinColumn

    private Song song;

    public SongAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongAlbum)) return false;
        SongAlbum that = (SongAlbum) o;
        return Objects.equals(song.getName(), that.song.getName()) &&
                Objects.equals(album.getTitle(), that.album.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(song.getName(), album.getTitle());
    }

    public void setSong(Song song) {
        this.song = song;
    }
}