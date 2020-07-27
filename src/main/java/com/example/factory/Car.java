package com.example.factory;

public class Car extends Product{
    @Override
    String getProductInfo() {
        return "I am a car";
    }
}
