package com.nwt.microservice.service;

import com.nwt.microservice.model.Genre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface GenreService {
    public ArrayList<Genre> getAllGenre();
    public Optional<Genre> getGenre(Integer id);
    public Genre changeGenre(Integer id, String name);
    public Optional<Genre> findById(Integer id);
    public Genre addGenre(String name);
    public Boolean deleteAllGenres();
    public Genre deleteGenreById(Genre id);


}
