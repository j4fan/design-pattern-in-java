package com.example.demo.generic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class GenericTest {

    @Test
    public void testList() {
        List list = new LinkedList();
        list.add(new Integer(1));
        Integer i = (Integer) list.iterator().next();
        Assert.assertTrue(i == 1);
    }

    @Test
    public void testTransfer() {
        Integer[] integers = new Integer[]{1, 2, 3};
        List<String> strings = fromArrayToList(integers, Object::toString);
        Assert.assertTrue(strings.size() > 0);
    }

    @Test
    public void testFromArrays() {
        Integer[] integers = new Integer[]{1, 2, 3};
        Double[] doubles = new Double[]{1.0, 2.0, 3.0};
        String[] strings = new String[]{"A", "B", "C"};
        List<Integer> integerList = fromArrays(integers);
        List<Double> doubleList = fromArrays(doubles);
//        List<String> stringList = fromArrays(strings);
    }

    @Test
    public void testPrintList() {
        List<String> list = new ArrayList();
        printList(list);
    }


    public static <T> List<T> fromArrayToList(T[] a) {
        return Arrays.asList(a).stream().collect(Collectors.toList());
    }

    public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
        return Arrays.asList(a).stream().map(mapperFunction).collect(Collectors.toList());
    }

    public static <T extends Number> List<T> fromArrays(T[] a) {
        return Arrays.asList(a).stream().collect(Collectors.toList());
    }

    public static <M> M getM(M m) {
        return m;
    }

    public static void printList(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
