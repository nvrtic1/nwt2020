package com.nwt.microservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class SongSinger implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Singer singer;

    @Id
    @ManyToOne
    @JoinColumn

    private Song song;

    public SongSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongSinger)) return false;
        SongSinger that = (SongSinger) o;
        return Objects.equals(song.getName(), that.song.getName()) &&
                Objects.equals(singer.getFirstname(), that.singer.getFirstname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(song.getName(), singer.getFirstname());
    }

    public void setSong(Song song) {
        this.song = song;
    }
}