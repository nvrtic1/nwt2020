package com.example.Performers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
//@Table//(name = "singer")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int singerId;

    @Column//(name = "name")
    private String name;

    @Column//(name = "biography")
    private String biography;

    @Column//(name = "birthday")
    private Date birthday;

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    Set<SingerNews> singerNews;

    /*
    @OneToMany(mappedBy = "singersongs", cascade = CascadeType.ALL)
    //@JsonIgnore
            Set<SingerSong> singerSongs = new HashSet<>();
    */
    public Singer(){}
    public Singer(int singerId, String name, String biography, Date birthday){
        this.singerId = singerId;
        this.name  = name;
        this.biography = biography;
        this.birthday = birthday;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<SingerNews> getSingerNews() {
        return singerNews;
    }

    public void setSingerNews(Set<SingerNews> singerNews) {
        this.singerNews = singerNews;
    }


/*
    public Singer(){

    }*/


}