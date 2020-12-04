package com.example;

import org.springframework.context.ApplicationEvent;

public class GenericSpringEvent<T> {
    private T what;
    protected boolean success;

    public T getWhat() {
        return what;
    }

    public boolean isSuccess() {
        return success;
    }

    public GenericSpringEvent(T what, boolean success) {
        this.what = what;
        this.success = success;
    }

}