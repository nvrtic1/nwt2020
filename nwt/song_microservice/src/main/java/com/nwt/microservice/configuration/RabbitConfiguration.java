package com.nwt.microservice.configuration;

import org.springframework.amqp.core.DirectExchange;

import org.springframework.amqp.core.Queue;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    public Queue userCreatedQueue() {
        return new Queue("user_created_queue");
    }
}
