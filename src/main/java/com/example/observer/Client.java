package com.example.observer;

public class Client {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new ReadObserver(subject);
        new WriteObserver(subject);
        subject.setState(1);
        subject.setState(2);
    }
}
