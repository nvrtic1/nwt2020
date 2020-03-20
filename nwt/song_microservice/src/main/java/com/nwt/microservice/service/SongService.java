package com.nwt.microservice.service;

import com.nwt.microservice.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface SongService {
    public ArrayList<Song> getAllSongs();
    public Optional<Song> getSong(Integer id);
    public Song changeSong(Integer id, String name, String description, Double rating, Integer length);
    public Optional<Song> findById(Integer id);
}
