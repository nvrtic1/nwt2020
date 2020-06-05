package com.nwt.microservice.repository;

import com.nwt.microservice.model.User;
import com.nwt.microservice.model.UserSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSongRepository extends JpaRepository<UserSong, Long> {
}
