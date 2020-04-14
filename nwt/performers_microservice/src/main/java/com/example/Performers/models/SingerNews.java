package com.example.Performers.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
//@NoArgsConstructor

@Entity
public class SingerNews implements Serializable {


    @Id
    private int singerNewsId;


    @Id
    @ManyToOne
    @JoinColumn//(name = "singer_id")
    private Singer singer;


    @ManyToOne
    @JoinColumn//(name = "news_id")
    private News news;

    LocalDateTime registredAt;


    public SingerNews(){}
    public SingerNews(News news) {
        this.news = news;
        registredAt = LocalDateTime.now();
    }

    public int getSingerNewsId() {
        return singerNewsId;
    }

    public void setSingerNewsId(int singerNewsId) {
        this.singerNewsId = singerNewsId;
    }

    public Singer getSinger() {
        return singer;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public LocalDateTime getRegistredAt() {
        return registredAt;
    }

    public void setRegistredAt(LocalDateTime registredAt) {
        this.registredAt = registredAt;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingerNews)) return false;
        SingerNews that = (SingerNews) o;
        return Objects.equals(singer.getName(), that.singer.getName()) &&
                Objects.equals(news.getTitle(), that.news.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(singer.getName(), news.getTitle());
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

}