package com.example.observer;

public class WriteObserver implements Observer{
    private Subject subject;

    WriteObserver(Subject subject){
        this.subject = subject;
        subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println("WriteObserver执行");
    }
}
