package net.springboot.user_micro_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    Queue queue() {
        return new Queue("user_created_queue");
    }
}
