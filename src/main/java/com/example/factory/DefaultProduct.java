package com.example.factory;

public class DefaultProduct extends Product{
    @Override
    String getProductInfo() {
        return "default product";
    }
}
