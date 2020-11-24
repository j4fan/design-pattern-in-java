package com.example.demo.reflect;

public class Bird extends Animal {

    private boolean walks;

    public Bird(){
        super("bird");
    }

    public boolean walks() {
        return walks;
    }

    public void setWalks(boolean walks){
        this.walks = walks;
    }

    public Bird(String name,boolean walks){
        super(name);
        this.walks = walks;
    }

    public Bird(String name) {
        super(name);
    }

    @Override
    protected String getSound() {
        return null;
    }

    @Override
    public String eats() {
        return null;
    }
}
