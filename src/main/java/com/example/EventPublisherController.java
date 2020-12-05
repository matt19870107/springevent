package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventPublisherController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionManager tansactionManager;

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

    @GetMapping("/event/insert")
    @Transactional
    public String insert() throws InterruptedException {
        User user = new User();
        user.setName("Matt");
        user.setSex("Boy");
        userRepository.save(user);
        /*if(1==1){
            throw new IllegalArgumentException();
        }*/
        GenericSpringEvent<String> genericEventListener = new GenericSpringEvent("transaction committed", true);
        applicationEventPublisher.publishEvent(genericEventListener);

        return "insert";
    }

    @GetMapping("/event/find")
    public String find(){
        boolean hasResult = false;
        StringBuffer bf = new StringBuffer();
        for (User tmpUser : userRepository.findAll()) {
            bf.append(tmpUser.toString() + "; ");
            System.out.println("The User is: " + tmpUser.toString());
            hasResult = true;
        }
        if(!hasResult){
            System.out.println("Not found!!!!");
        }
        return bf.toString();
    }

}
