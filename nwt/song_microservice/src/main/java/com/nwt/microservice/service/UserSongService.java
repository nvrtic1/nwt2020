package com.nwt.microservice.service;

import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Song;
import com.nwt.microservice.model.User;
import com.nwt.microservice.model.UserSong;
import com.nwt.microservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserSongService {
    private SongRepository songRepository;
    private AlbumRepository albumRepository;
    private GenreRepository genreRepository;
    private ReviewRepository reviewRepository;
    private UserSongRepository userSongRepository;

    @Autowired
    public UserSongService(SongRepository songRepository, UserSongRepository userSongRepository){
        this.songRepository = songRepository;
        this.userSongRepository = userSongRepository;
    }

    public List<UserSong> getSongByEmail(String email)
    {
        List<UserSong> lista = userSongRepository.findAll();
        List<UserSong> lista2 = new ArrayList<UserSong>();

        for(int i=0;i<lista.size(); i++)
        {
            if(lista.get(i).getEmail().equals(email))
            {
                lista2.add(lista.get(i));
            }
        }

        return lista2;
    }

    public void deleteSongByEmail(String email)
    {
        List<UserSong> lista = userSongRepository.findAll();

        for(int i=0;i<lista.size(); i++)
        {
            if(lista.get(i).getEmail().equals(email))
            {
                userSongRepository.delete(lista.get(i));
            }
        }
    }

    public UserSong addUserSong(UserSong userSong)
    {
        userSongRepository.save(userSong);
        return userSong;
    }

}
