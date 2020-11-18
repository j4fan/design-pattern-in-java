package com.example.demo.reflect;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestReflect {

    @Test
    public void testClassName(){
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();
        Assert.assertEquals("Goat",clazz.getSimpleName());
        Assert.assertEquals("com.example.demo.reflect.Goat",clazz.getName());
        Assert.assertEquals("com.example.demo.reflect.Goat",clazz.getCanonicalName());
    }

}
