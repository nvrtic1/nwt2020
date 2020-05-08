package com.example.systemevents;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@EnableEurekaClient
@SpringBootApplication
public class Application {

	//grpc server
	public static void main(String[] args) throws IOException, InterruptedException{
		SpringApplication.run(Application.class, args);
        Server server = ServerBuilder.forPort(8082).addService(new SystemEventsImpl()).build();
        server.start();
        server.awaitTermination();
    }
}
