package com.nwt.microservice.controller;

import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Song;
import com.nwt.microservice.model.UserSong;
import com.nwt.microservice.repository.SongRepository;
import com.nwt.microservice.service.SongServiceImpl;
import com.nwt.microservice.service.UserSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserSongController {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserSongService userSongService;

    /*
    @CrossOrigin(origins = "*")
    @GetMapping("/songs")
    public List<Song> getListAllSongs() {
        if(songRepository.findAll().size() == 0)
            throw  new ResourceNotFoundException("Nije pronadjena ni jedana pjesma!");
        return songService.getAllSongs();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/songs/{id}")
    public Optional<Song> getSongById(@PathVariable Integer id){
        if(songRepository.findById(id).isEmpty())
            throw  new ResourceNotFoundException("Nije pronadjena pjesma sa ID-jem: "+id);
        return songService.getSong(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/songs")
    public Song newSong(@Valid @RequestBody Song song) {
        return songService.addSong(song);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/songs/")
    public ResponseEntity<?> deleteAllSongs() {
        songService.deleteAllSongs();
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/songs/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Integer id) {
        return songService.findById(id).map(song -> {
            songService.deleteSongById(song);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Pjesma sa ID-jem " + id + " nije pronađena"));
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable Integer songId, @Valid @RequestBody Song newSong) {
        return songService.changeSong(songId, newSong.getName(), newSong.getDescription(), newSong.getRating(), newSong.getLength());
    }

     */

    @CrossOrigin(origins = "*")
    @GetMapping("/userSong/{email}")
    public List<UserSong> getSongByEmail(@PathVariable String email){

        return userSongService.getSongByEmail(email);
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/userSong")
    public UserSong newUserSong(@Valid @RequestBody UserSong userSong) {
        return userSongService.addUserSong(userSong);
    }


}
