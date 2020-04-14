package com.example.Performers.controler;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import com.netflix.discovery.shared.Application;
import com.example.Performers.exception.ResourceNotFoundException;
import com.example.Performers.models.Singer;
import com.example.Performers.repository.SingerRepository;
import com.example.Performers.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class SinhronaSongController {
/*
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.songService.serviceAll}")
    private String songServiceServiceAll;


    @RequestMapping("/songs")
    public List<Object> findAllSongs() {

        Application application = eurekaClient.getApplication(songServiceServiceAll);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        System.out.println(instanceInfo);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "songs";
        System.out.println("URL" + url);

        try {
            List<Object> result = restTemplate.getForObject(url, List.class);
            return result;
        }
        catch (Exception e)
        {
            if(1==1) throw  new ResourceNotFoundException("Provjerite da li je upaljen drugi mikroservis!");
            System.out.println("Greska");
            return null;
        }

     }

 */
}
