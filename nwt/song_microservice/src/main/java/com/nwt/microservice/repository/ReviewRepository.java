package com.nwt.microservice.repository;

import com.nwt.microservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
    List<Review> findBySong(Integer songId);
}