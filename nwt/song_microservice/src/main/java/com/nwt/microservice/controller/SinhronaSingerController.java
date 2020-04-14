package com.nwt.microservice.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import com.netflix.discovery.shared.Application;
import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Song;
import com.nwt.microservice.repository.SongRepository;
import com.nwt.microservice.service.GenreServiceImpl;
import com.nwt.microservice.service.SinhronaServiceImpl;
import com.nwt.microservice.service.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SinhronaSingerController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.singerService.serviceAll}")
    private String singerServiceServiceAll;

    @Autowired
    private SinhronaServiceImpl sinhronaService;


    @RequestMapping("/singers")
    public List<Object> findAllSingers() {
        if(sinhronaService.findAllSingers().size() == 0)
            throw  new ResourceNotFoundException("Nije pronadjen ni jedan pjevač!");
        return sinhronaService.findAllSingers();
    }

    @RequestMapping("/singers/{id}")
    public Object findSingerByID(@PathVariable Integer id) {
        if(sinhronaService.findSingerByID(id)==null)
            throw  new ResourceNotFoundException("Nije pronadjen pjevač sa ID-jem: "+id);
        return sinhronaService.findSingerByID(id);
    }


}
