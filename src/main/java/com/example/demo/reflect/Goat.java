package com.example.demo.reflect;

public class Goat extends Animal implements Locomotion{

    @Override
    protected String getSound() {
        return "bleat";
    }

    @Override
    public String eats() {
        return "grass";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }


    public Goat(String name) {
        super(name);
    }


}
