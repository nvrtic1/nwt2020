package com.nwt.microservice.service;

import com.netflix.discovery.converters.Auto;
import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Song;
import com.nwt.microservice.repository.AlbumRepository;
import com.nwt.microservice.repository.SongRepository;
import com.nwt.microservice.repository.ReviewRepository;
import com.nwt.microservice.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;
    private AlbumRepository albumRepository;
    private GenreRepository genreRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, GenreRepository genreRepository, ReviewRepository reviewRepository){
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.reviewRepository = reviewRepository;
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

    @Override
    public Boolean deleteAllSongs()
    {
        songRepository.deleteAll();
        return true;
    }


    @Override
    public Song deleteSongById(Song id)
    {
        return songRepository.findById(id.getID()).map(song -> {
            songRepository.delete(song);
            return song;
        }).orElseThrow(() -> new ResourceNotFoundException("Pjesma sa ID-jem " + id + " nije pronađena"));
    }

}
