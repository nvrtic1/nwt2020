package com.example.Performers.service;

import com.example.Performers.models.Singer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public interface SingerServiceInterface {
    public ArrayList<Singer> getAllSingers();
    public Optional<Singer> getSinger(Integer id);
    public Singer changeSinger(Integer id, String name, String biography, Date birthday);
    public Optional<Singer> findById(Integer id);

}
