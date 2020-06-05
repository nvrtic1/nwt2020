package com.nwt.microservice.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwt.microservice.model.User;
import com.nwt.microservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;


    private UserSongService userSongService;

    @RabbitListener(queues = "#{userCreatedQueue.name}")
    public void getCreateUserMessage(String userCreatedMessage) {
        logger.info("++++++++++++++++++++++++++1 {}", userCreatedMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            user = objectMapper.readValue(userCreatedMessage, User.class);
        } catch (IOException e) {
            logger.info(String.valueOf(e.getMessage()));
        }
        userRepository.save(user);
        logger.debug("User {} saved to DB", user.toString());
    }

    @RabbitListener(queues = "#{userDeleteQueue.name}")
    public void getDeleteUserMessage(String userDeleteMessage) {
        logger.info("++++++++++++++++++++++++++2 {}", userDeleteMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            user = objectMapper.readValue(userDeleteMessage, User.class);
        } catch (IOException e) {
            logger.info(String.valueOf(e.getMessage()));
        }
        System.out.println(user.toString());
        userRepository.delete(user);
        
        logger.debug("User {} deleted from DB", user.toString());
    }

}
