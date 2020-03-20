package com.nwt.microservice.repository;

import com.nwt.microservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer>{
}