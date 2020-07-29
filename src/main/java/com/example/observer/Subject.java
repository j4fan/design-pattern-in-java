package com.example.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observerList = new ArrayList<>();

    private int state;

    public void attach(Observer observer){
        observerList.add(observer);
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    private void notifyObservers(){
        for(Observer observer : observerList){
            observer.update();
        }
    }
}
