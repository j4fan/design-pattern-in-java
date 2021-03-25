package com.example.demo.java8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author fanqingyuan
 */
@RunWith(JUnit4.class)
public class FunctionalInterfaceTest {

    @Test
    public void testFunctionalInterface(){
        DoWork doWork = ()-> System.out.println("gaga");
        doWork.work();
    }


}


