package com.example.observer;

public class ReadObserver implements Observer {

    private Subject subject;

    ReadObserver(Subject subject){
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("ReadObserver执行");
    }
}
