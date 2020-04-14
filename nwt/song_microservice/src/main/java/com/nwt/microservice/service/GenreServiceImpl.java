package com.nwt.microservice.service;

import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Genre;
import com.nwt.microservice.repository.AlbumRepository;
import com.nwt.microservice.repository.GenreRepository;
import com.nwt.microservice.repository.ReviewRepository;
import com.nwt.microservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private SongRepository songRepository;
    private AlbumRepository albumRepository;
    private GenreRepository genreRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public GenreServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, GenreRepository genreRepository, ReviewRepository reviewRepository){
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ArrayList<Genre> getAllGenre(){
        ArrayList<Genre> genres = new ArrayList<>();
        genreRepository.findAll().forEach(genres::add);
        return genres;
    }

    @Override
    public Optional<Genre> getGenre(Integer id){
        return genreRepository.findById(id);
    }

    @Override
    public Genre changeGenre(Integer id, String name){
        return genreRepository.findById(id).map(genre -> {
            genre.setName(name);
            return genre;
        }).orElseThrow(() -> new ResourceNotFoundException("Žanr sa ID-jem: " + id + " nije pronadjen"));
    }

    @Override
    public Optional<Genre> findById(Integer id){
        return genreRepository.findById(id);
    }

    @Override
    public Genre addGenre(String name)
    {
        Genre g = new Genre(name);
        genreRepository.save(g);
        return g;
    }

    @Override
    public Boolean deleteAllGenres()
    {
        genreRepository.deleteAll();
        return true;
    }

    @Override
    public Genre deleteGenreById(Genre id)
    {
        return genreRepository.findById(id.getId()).map(genre -> {
            genreRepository.delete(genre);
            return genre;
        }).orElseThrow(() -> new ResourceNotFoundException("Žanr sa ID-jem " + id + " nije pronađena"));
    }


}
