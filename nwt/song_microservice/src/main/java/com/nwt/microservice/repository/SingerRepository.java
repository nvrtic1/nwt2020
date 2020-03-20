package com.nwt.microservice.repository;

import com.nwt.microservice.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer, Integer>{
}