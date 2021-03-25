package com.example.demo.stream;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

@RunWith(JUnit4.class)
public class RandomList {

    @Test
    public void shuffleName(){
        List<String> nameList = Lists.newArrayList("朱鹏","王培培","邬志罡","张志伟","胡文琪","李俊飞","范清源");
        Collections.shuffle(nameList);
        nameList.stream().forEach(System.out::println);
    }
}
