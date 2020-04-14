package com.nwt.microservice.service;

import com.nwt.microservice.model.Album;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public interface AlbumService {
    public ArrayList<Album> getAllAlbums();
    public Optional<Album> getAlbum(Integer id);
    public Album changeAlbum(Integer id, String title, String description, Date dateRelease);
    public Optional<Album> findById(Integer id);
    public Boolean deleteAllAlbums();
    public Album addAlbum(Album album);
    public Album deleteAlbumById(Album id);


}
