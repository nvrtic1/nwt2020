package com.nwt.microservice.service;

import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Song;
import com.nwt.microservice.repository.SingerRepository;
import com.nwt.microservice.repository.SongRepository;
import com.nwt.microservice.repository.Song_SessionRepository;
import com.nwt.microservice.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;
    private SingerRepository singerRepository;
    private GenreRepository genreRepository;
    private Song_SessionRepository song_SessionRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, SingerRepository singerRepository, GenreRepository genreRepository, Song_SessionRepository song_SessionRepository){
        this.songRepository = songRepository;
        this.singerRepository = singerRepository;
        this.genreRepository = genreRepository;
        this.song_SessionRepository = song_SessionRepository;
    }

    @Override
    public ArrayList<Song> getAllSongs(){
        ArrayList<Song> songs = new ArrayList<>();
        songRepository.findAll().forEach(songs::add);
        return songs;
    }

    @Override
    public Optional<Song> getSong(Integer id){
        return songRepository.findById(id);
    }

    @Override
    public Song changeSong(Integer id, String name, String description, Double rating, Integer length){
        return songRepository.findById(id).map(song -> {
            song.setName(name);
            song.setDescription(description);
            song.setRating(rating);
            song.setLength(length);
            return song;
        }).orElseThrow(() -> new ResourceNotFoundException("Pjesma sa ID-jem: " + id + " nije pronadjena"));
    }

    @Override
    public Optional<Song> findById(Integer id){
        return songRepository.findById(id);
    }
}
