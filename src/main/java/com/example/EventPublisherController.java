package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Predicate;

@RestController
public class EventPublisherController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/event/{message}")
    public String postEvent(@PathVariable("message") String message){

        System.out.println("Publishing custom event: " + message);
        GenericSpringEvent<String> genericEventListener = new GenericSpringEvent(message, true);
        applicationEventPublisher.publishEvent(genericEventListener);
        System.out.println("Publishing custom event finished: " + message);
        return "Post";
    }

    @GetMapping("/event/{message}")
    public String getEvent(@PathVariable("message") String message) throws InterruptedException {

       this.postEvent(message);
        return "Post";
    }

}
