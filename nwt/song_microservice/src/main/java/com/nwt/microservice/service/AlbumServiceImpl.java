package com.nwt.microservice.service;

import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Album;
import com.nwt.microservice.repository.AlbumRepository;
import com.nwt.microservice.repository.GenreRepository;
import com.nwt.microservice.repository.ReviewRepository;
import com.nwt.microservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private SongRepository songRepository;
    private AlbumRepository albumRepository;
    private GenreRepository genreRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public AlbumServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, GenreRepository genreRepository, ReviewRepository reviewRepository){
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public ArrayList<Album> getAllAlbums(){
        ArrayList<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Optional<Album> getAlbum(Integer id){
        return albumRepository.findById(id);
    }

    @Override
    public Album changeAlbum(Integer id, String title, String description, Date dateRelease){
        return albumRepository.findById(id).map(album -> {
            album.setTitle(title);
            album.setDescription(description);
            album.setDateRelease(dateRelease);
            return album;
        }).orElseThrow(() -> new ResourceNotFoundException("Album sa ID-jem: " + id + " nije pronadjen"));
    }

    @Override
    public Optional<Album> findById(Integer id){
        return albumRepository.findById(id);
    }

    @Override
    public Boolean deleteAllAlbums()
    {
        albumRepository.deleteAll();
        return true;
    }

    @Override
    public Album addAlbum(Album album)
    {
        albumRepository.save(album);
        return album;
    }

    @Override
    public Album deleteAlbumById(Album id)
    {
        return albumRepository.findById(id.getId()).map(album -> {
            albumRepository.delete(album);
            return album;
        }).orElseThrow(() -> new ResourceNotFoundException("Album sa ID-jem " + id + " nije pronađena"));
    }

}
