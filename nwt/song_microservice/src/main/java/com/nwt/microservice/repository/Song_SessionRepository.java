package com.nwt.microservice.repository;

import com.nwt.microservice.model.Song_Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Song_SessionRepository extends JpaRepository<Song_Session, Integer>{
    List<Song_Session> findBySong(Integer songId);
}