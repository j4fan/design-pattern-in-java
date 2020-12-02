package com.example.demo.stream;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(JUnit4.class)
public class StreamTest {

    @Test
    public void testSummary(){
        Stream<Integer> integerStream = Stream.of(1,2,3);
        Optional<Integer> sum = integerStream.reduce(Integer::sum);
        Assert.assertEquals(6,sum);
    }

    @Test
    public void testSummaryList(){
        Person p1 = new Person();
        p1.setAge(1);
        Person p2 = new Person();
        p2.setAge(2);
        Person p3 = new Person();
        p3.setAge(3);
        Stream<Person> stream = Stream.of(p1,p2,p3);
        long sum = stream.collect(Collectors.summarizingInt(Person::getAge)).getSum();
        Assert.assertEquals(6,sum);
    }

    @Data
    class Person{
        private String name;
        private Integer age;
    }
}
