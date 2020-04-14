package com.example.Performers.repository;

import com.example.Performers.models.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer, Integer> {
}
