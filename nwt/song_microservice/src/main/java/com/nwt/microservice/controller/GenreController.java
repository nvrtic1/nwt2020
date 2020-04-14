package com.nwt.microservice.controller;

import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Genre;
import com.nwt.microservice.repository.GenreRepository;
import com.nwt.microservice.service.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GenreController {

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreServiceImpl genreService;

    @GetMapping("/genre")
    public List<Genre> getListAllGenre() {
        if(genreService.getAllGenre().size() == 0)
            throw  new ResourceNotFoundException("Nije pronadjen ni jedan zanr!");
        return genreService.getAllGenre();
    }

    @GetMapping("/genre/{id}")
    public Optional<Genre> getGenreById(@PathVariable Integer id){
        if(genreService.findById(id)==null)
            throw  new ResourceNotFoundException("Nije pronadjen žanr sa ID-jem: "+id);
        return genreService.getGenre(id);
    }

    @PostMapping("/genre")
    public Genre newGenre(@Valid @RequestBody String name) {
        return genreService.addGenre(name);
    }

    @DeleteMapping("/genre")
    public ResponseEntity<?> deleteAllGenre() {
        genreService.deleteAllGenres();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/genre/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable Integer genreId) {
        return genreService.findById(genreId).map(genre -> {
            genreService.deleteGenreById(genre);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Zanr sa ID-jem " + genreId + " nije pronađena"));
    }

    @PutMapping("/genre/{genreId}")
    public Genre updateGenre(@PathVariable Integer genreId, @Valid @RequestBody Genre newGenre) {
        return genreService.changeGenre(genreId, newGenre.getName());
    }
}
