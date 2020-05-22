package net.springboot.user_micro_service.service;

import net.springboot.user_micro_service.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.Queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;

import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler
public class UserEventHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RabbitTemplate rabbitTemplate;

    private Queue userCreatedQueue;
    private Queue userDeleteQueue;


    @Autowired
    public UserEventHandler(RabbitTemplate rabbitTemplate, Queue userCreatedQueue, Queue userDeleteQueue){
        this.rabbitTemplate = rabbitTemplate;

        this.userCreatedQueue = userCreatedQueue;
        this.userDeleteQueue = userDeleteQueue;
    }

    @HandleAfterCreate
    public void handleAfterCreated(User user) {
        rabbitTemplate.convertAndSend(userCreatedQueue.getName(), serializeToJson(user));
    }

    @HandleAfterDelete
    public void handleAfterDelete(User user) {
        rabbitTemplate.convertAndSend("user_delete_queue", serializeToJson(user));
    }

    private String serializeToJson(User user) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            logger.info(String.valueOf(e));
        }
        logger.debug("Serialized message payload: {}", jsonInString);
        return jsonInString;
    }
}
