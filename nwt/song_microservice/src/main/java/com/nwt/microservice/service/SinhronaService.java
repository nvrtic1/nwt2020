package com.nwt.microservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SinhronaService {
        public List<Object> findAllSingers();
        public Object findSingerByID(Integer id);
    }
