package com.nwt.microservice.controller;

import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Song;
import com.nwt.microservice.repository.SongRepository;
import com.nwt.microservice.service.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SongController {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongServiceImpl songService;

    @GetMapping("/songs")
    public List<Song> getListAllSongs() {
        if(songRepository.findAll().size() == 0)
            throw  new ResourceNotFoundException("Nije pronadjena ni jedana pjesma!");
        return songService.getAllSongs();
    }

    @GetMapping("/songs/{id}")
    public Optional<Song> getSongById(@PathVariable Integer id){
        if(songRepository.findById(id)==null)
            throw  new ResourceNotFoundException("Nije pronadjena pjesma sa ID-jem: "+id);
        return songService.getSong(id);
    }

    @PostMapping("/songs")
    public Song newSong(@Valid @RequestBody Song song) {
        return songRepository.save(song);
    }

    @DeleteMapping("/songs/")
    public ResponseEntity<?> deleteAllSongs() {
        songRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/songs/{songid}")
    public ResponseEntity<?> deleteSong(@PathVariable Integer songId) {
        return songRepository.findById(songId).map(song -> {
            songRepository.delete(song);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Pjesma sa ID-jem " + songId + " nije pronađena"));
    }
}
