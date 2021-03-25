package com.example.demo.java8;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

@RunWith(JUnit4.class)
public class OptionalTest {

    private Provice provice;

    @Before
    public void prepare() {
        Distrit distrit = new Distrit("街道1");
        City city = new City(distrit);
        provice = null;
    }

    @Test
    public void testOptionalPrint() {
        Optional result = Optional.ofNullable(provice).map(Provice::getCity).map(City::getDistrict).map(Distrit::getName);
        if (result.isPresent()) {
            System.out.println(result.get());
        }

        //
        String resultNonNull = Optional.ofNullable(provice).map(Provice::getCity).map(City::getDistrict).map(Distrit::getName).orElse("");

    }

    @Test
    public void testNPE() {
        System.out.println(provice.getCity().getDistrict().getName());
    }

    @Test
    public void testOriginPrint() {
        if (provice != null) {
            City city = provice.getCity();
            if (city != null) {
                Distrit distrit = city.getDistrict();
                if (distrit != null) {
                    System.out.println(distrit.getName());
                }
            }
        }
    }
}
