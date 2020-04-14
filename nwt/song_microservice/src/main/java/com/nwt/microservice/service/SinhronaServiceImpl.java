package com.nwt.microservice.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.nwt.microservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SinhronaServiceImpl implements SinhronaService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.singerService.serviceAll}")
    private String singerServiceServiceAll;


    public List<Object> findAllSingers()
    {
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
    public Object findSingerByID(Integer id)
    {
        Application application = eurekaClient.getApplication(singerServiceServiceAll);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        System.out.println(instanceInfo);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "singers/" + id;
        System.out.println("URL: " + url);

        try {
            Object result = restTemplate.getForObject(url, Object.class);
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
