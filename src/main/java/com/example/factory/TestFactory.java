package com.example.factory;

public class TestFactory {

    public static void main(String[] args) {
        Product car = ProductFactory.createProduct("car");
        System.out.println(car.getProductInfo());
        Product train = ProductFactory.createProduct("train");
        System.out.println(train.getProductInfo());
        Product defaultProduct = ProductFactory.createProduct("others");
        System.out.println(defaultProduct.getProductInfo());
    }
}
