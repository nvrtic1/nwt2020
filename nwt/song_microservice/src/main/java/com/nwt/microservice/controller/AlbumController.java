package com.nwt.microservice.controller;

import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Album;
import com.nwt.microservice.repository.AlbumRepository;
import com.nwt.microservice.service.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AlbumController {

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumServiceImpl albumService;

    @GetMapping("/albums")
    public List<Album> getListAllAlbums() {
        if(albumRepository.findAll().size() == 0)
            throw  new ResourceNotFoundException("Nije pronadjen ni jedan album!");
        return albumService.getAllAlbums();
    }

    @GetMapping("/albums/{id}")
    public Optional<Album> getAlbumById(@PathVariable Integer id){
        if(albumRepository.findById(id).isEmpty())
            throw  new ResourceNotFoundException("Nije pronadjen album sa ID-jem: "+id);
        return albumService.getAlbum(id);
    }

    @PostMapping("/albums")
    public Album newAlbum(@Valid @RequestBody Album album) {
        return albumService.addAlbum(album);
    }

    @DeleteMapping("/albums/")
    public ResponseEntity<?> deleteAllAlbums() {
        albumService.deleteAllAlbums();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/albums/{albumId}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Integer albumId) {
        return albumService.findById(albumId).map(album -> {
            albumService.deleteAlbumById(album);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Album sa ID-jem " + albumId + " nije pronađena"));
    }

    @PutMapping("/albums/{albumId}")
    public Album updateAlbum(@PathVariable Integer albumId, @Valid @RequestBody Album album) {
        return albumService.changeAlbum(albumId, album.getTitle(), album.getDescription(), album.getDateRelease());
    }
}
