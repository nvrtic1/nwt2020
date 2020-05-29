package com.example.Performers.controler;


import com.example.Performers.exception.ResourceNotFoundException;
import com.example.Performers.models.Singer;
import com.example.Performers.repository.SingerRepository;
import com.example.Performers.service.SingerService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SingerControler {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private SingerService singerService;

    @CrossOrigin(origins = "*")
    @GetMapping("/singers")
    public List<Singer> getAllSingers(){
        List<Singer> singers = singerRepository.findAll();
        if(singers.size() == 0){
            throw new ResourceNotFoundException("There are no singers!");
        }
        return singers;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/singers/{id}")
    public Optional<Singer> getSingerById(@PathVariable Integer id){
        if(singerRepository.findById(id).isEmpty())
            throw  new ResourceNotFoundException("There is no singer with ID: "+id);
        return singerService.getSinger(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/singers")
    public Singer newSinger(@Valid @RequestBody Singer singer) {
        return singerRepository.save(singer);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/singers/")
    public ResponseEntity<?> deleteAllSingers() {
        singerRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/singers/{singerId}")
    public ResponseEntity<?> deleteSinger(@PathVariable("singerId") Integer singerId) {
        return singerRepository.findById(singerId).map(singer -> {
            singerRepository.delete(singer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Singer with ID " + singerId + " is not found"));
    }



}
