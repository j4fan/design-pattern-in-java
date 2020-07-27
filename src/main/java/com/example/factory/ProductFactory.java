package com.example.factory;

public class ProductFactory {

    public static Product createProduct(String name){
        if(name.equals("car")){
            return new Car();
        }
        if(name.equals("train")){
            return new Train();
        }
        return new DefaultProduct();
    }

}
