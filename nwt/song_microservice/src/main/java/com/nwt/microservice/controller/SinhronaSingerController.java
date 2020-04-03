package com.nwt.microservice.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import com.netflix.discovery.shared.Application;
import com.nwt.microservice.exception.ResourceNotFoundException;
import com.nwt.microservice.model.Song;
import com.nwt.microservice.repository.SongRepository;
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


    @RequestMapping("/singers")
    public List<Object> findAllHalls() {
        Application application = eurekaClient.getApplication(singerServiceServiceAll);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        System.out.println(instanceInfo);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "singers";
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

}
