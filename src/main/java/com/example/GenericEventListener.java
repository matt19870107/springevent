package com.example;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service("genericEventListener")
public class GenericEventListener{

    //It'll become sync if there is no ApplicationEventMulticaster definition.
    @EventListener(condition = "#event.success", classes = GenericSpringEvent.class)
    public void handleSuccessful(GenericSpringEvent<String> event) throws InterruptedException {
        //Thread.sleep(5000);
        System.out.println("Handling generic event (conditional)："+event.getWhat());
    }

    //It is async due to the annotation @Async no matter ApplicationEventMulticaster exist or not.
    @EventListener(condition = "#event.success", classes = GenericSpringEvent.class)
    @Async
    public void handleSuccessfulAsync(GenericSpringEvent<String> event) throws InterruptedException {
        //Thread.sleep(5000);
        System.out.println("Handling async annotation generic event (conditional)："+event.getWhat());
    }

    @TransactionalEventListener(classes = GenericSpringEvent.class)
    @Async
    public void handleTransactionCommit(GenericSpringEvent<String> event) throws InterruptedException {
        System.out.println("Handling Transaction commit!!!"+ event.getWhat());
    }

}
