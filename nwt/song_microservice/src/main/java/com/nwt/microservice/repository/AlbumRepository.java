package com.nwt.microservice.repository;

import com.nwt.microservice.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer>{
}