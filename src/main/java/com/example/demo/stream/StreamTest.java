package com.example.demo.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(JUnit4.class)
public class StreamTest {

    @Test
    public void testSummary() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Optional<Integer> sum = integerStream.reduce(Integer::sum);
        Assert.assertEquals(6, sum);
    }

    @Test
    public void testSummaryList() {
        Person p1 = new Person();
        p1.setAge(1);
        Person p2 = new Person();
        p2.setAge(2);
        Person p3 = new Person();
        p3.setAge(3);
        Stream<Person> stream = Stream.of(p1, p2, p3);
        long sum = stream.collect(Collectors.summarizingInt(Person::getAge)).getSum();
        Assert.assertEquals(6, sum);
    }

    @Test
    public void testFlatMap() {
        List<List<String>> highList = new ArrayList<>();
        highList.add(Lists.newArrayList("HH", "GG"));
        highList.add(Lists.newArrayList("HH", "NN"));
        List<String> flatList = highList.stream().flatMap(List::stream).filter(i -> i.equals("HH")).collect(Collectors.toList());
        System.out.println(flatList.size());
    }

    @Test
    public void splitList() {
        List<String> strings = Lists.newArrayList("1", "2", "3", "4", "5");
        List<List<String>> parts = Lists.partition(strings, 1000);
        Assert.assertTrue(parts.size() == 2);
    }

    @Test
    public void streamMap() {
        List<String> originList = new ArrayList<>();
        originList.add("A");
        originList.add("B");
        originList.add("C");
        List<String> result = originList.stream().map(i -> i.equals("A") ? i : i + "B").collect(Collectors.toList());
        Assert.assertTrue(originList.contains("AB"));
    }

    @Test
    public void testToMap() {
        List<Integer> originList = Lists.newArrayList(1, 2, 3, 4);
        Map<Integer, Integer> map = originList.stream().collect(Collectors.toMap(i -> i, i -> i + 1));
        Assert.assertTrue(map.entrySet().size() > 0);
    }

    @Test
    public void testGroupby() {
        List<Integer> originList = Lists.newArrayList(1, 2, 3, 4);
        Map<Boolean, List<Integer>> map = originList.stream().collect(Collectors.groupingBy(i -> i > 2, Collectors.toList()));
        Assert.assertTrue(map.entrySet().size() > 0);
    }

    @Test
    public void partitionBy() {
        List<Integer> originList = Lists.newArrayList(1, 2, 3, 4);
        Map<Boolean, List<Integer>> map = originList.stream().collect(Collectors.partitioningBy(i -> i > 2, Collectors.toList()));
    }

    @Test
    public void testDebug() {
        System.out.println("gg");
        try {
            int a = 1 + 1;
            System.out.println("mm");
        } catch (Exception e) {
            System.out.println("gg");
        }

    }

    @Test
    public void testGroupingBy() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("ff", 1));
        personList.add(new Person("ff", 3));
        personList.add(new Person("mm", 2));

        Map<String, Double> map = personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        System.out.println("gg");
    }

    @Test
    public void testStream() {
        BigDecimal amount1 = BigDecimal.valueOf(1.00);
        BigDecimal amount2 = BigDecimal.valueOf(2.00);
        BigDecimal amount3 = BigDecimal.valueOf(3.00);
        List<BigDecimal> list = Lists.newArrayList(amount1, amount2, amount3);
        BigDecimal sum = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("gg");
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Person {
        private String name;
        private Integer age;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Term {
        private Integer param1;
        private Integer param2;
        private Integer param3;
    }

    @Test
    public void testCompare() {
        Term term1 = new Term(10, 5, 1);
        Term term2 = new Term(10, 5, 2);
        Term term3 = new Term(9, 2, 3);
        Term term4 = new Term(10, 4, 4);
        Term term5 = new Term(11, 2, 5);
        List<Term> terms = Lists.newArrayList(term1, term2, term3, term4, term5);

        terms.sort(Comparator.comparing(Term::getParam1).
                thenComparing(Term::getParam2).thenComparing(Term::getParam3));

        System.out.println("gg");

        List<Term> result = terms.stream().sorted(Comparator.comparing(Term::getParam1).
                thenComparing(Term::getParam2).thenComparing(Term::getParam3)).collect(Collectors.toList());
        System.out.println(result.get(0));
        System.out.println("gg");
    }

}
