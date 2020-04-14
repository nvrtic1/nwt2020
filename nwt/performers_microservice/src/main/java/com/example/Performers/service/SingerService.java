package com.example.Performers.service;

import com.example.Performers.exception.ResourceNotFoundException;
import com.example.Performers.models.Singer;
import com.example.Performers.repository.NewsRepository;
import com.example.Performers.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class SingerService implements SingerServiceInterface {

    private SingerRepository singerRepository;
    private NewsRepository newsRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository, NewsRepository newsRepository){
        this.singerRepository = singerRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public ArrayList<Singer> getAllSingers() {
        ArrayList<Singer> singers = new ArrayList<>();
        singers.addAll(singerRepository.findAll());
        return singers;
    }

    @Override
    public Optional<Singer> getSinger(Integer id) {
        return singerRepository.findById(id);
    }

    @Override
    public Singer changeSinger(Integer id, String name, String biography, Date birthday) {
        return singerRepository.findById(id).map(singer -> {
            singer.setName(name);
            singer.setBiography(biography);
            singer.setBirthday(birthday);
            return singer;
        }).orElseThrow(()->new ResourceNotFoundException("Singer with ID: " + id + " is not found"));
    }

    @Override
    public Optional<Singer> findById(Integer id) {
        return singerRepository.findById(id);
    }
}
