package com.example.Performers.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Data
@Entity
//@Table//(name = "news")
public class News {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int newsId;

    //@Column//(name = "title")
    String title;

    //@Column(name = "text")
    String text;

    /*
    @Column(name = "song")
    Song song;
    */
    public News(){}
    public News(int newsId, String title, String text) {
        this.newsId = newsId;
        this.title = title;
        this.text = text;
    }



    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    Set<SingerNews> singerNews;

    //TODO: provjeriti da li staviti String/id ili napraviti model za User
    @Column//(name = "user")
    String user;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Set<SingerNews> getSingerNews() {
        return singerNews;
    }

    public void setSingerNews(Set<SingerNews> singerNews) {
        this.singerNews = singerNews;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}