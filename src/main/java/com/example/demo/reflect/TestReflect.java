package com.example.demo.reflect;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TestReflect {

    @Test
    public void testClassName() {
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();
        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.example.demo.reflect.Goat", clazz.getName());
        assertEquals("com.example.demo.reflect.Goat", clazz.getCanonicalName());
    }

    @Test
    public void testModifiers() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.example.demo.reflect.Goat");
        Class<?> animalClass = Class.forName("com.example.demo.reflect.Animal");

        int goatMods = goatClass.getModifiers();
        int animalMods = animalClass.getModifiers();

        Assert.assertTrue(Modifier.isPublic(goatMods));
        Assert.assertTrue(Modifier.isAbstract(animalMods));
        Assert.assertTrue(Modifier.isPublic(animalMods));
    }

    @Test
    public void testPackage() {
        Goat goat = new Goat("goat");
        Class<?> goatClass = goat.getClass();
        Package pkg = goatClass.getPackage();

        assertEquals("com.example.demo.reflect", pkg.getName());
    }

    @Test
    public void testSuperClass() {
        Goat goat = new Goat("goat");
        String str = "any string";

        Class<?> goatClass = goat.getClass();
        Class<?> goatSuperClass = goatClass.getSuperclass();

        assertEquals("Animal", goatSuperClass.getSimpleName());
        assertEquals("Object", str.getClass().getSuperclass().getSimpleName());
    }

    @Test
    public void testImplementedInterfaces() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.example.demo.reflect.Goat");
        Class<?> animalClass = Class.forName("com.example.demo.reflect.Animal");

        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();

        assertEquals(1, goatInterfaces.length);
        assertEquals(1, animalInterfaces.length);
        assertEquals("Locomotion", goatInterfaces[0].getSimpleName());
        assertEquals("Eating", animalInterfaces[0].getSimpleName());
    }

    @Test
    public void testConstructors() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.example.demo.reflect.Goat");

        Constructor<?>[] constructors = goatClass.getConstructors();

        assertEquals(1, constructors.length);
        assertEquals("com.example.demo.reflect.Goat", constructors[0].getName());
    }

    @Test
    public void testMethod() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("com.example.demo.reflect.Animal");
        Field[] fields = animalClass.getDeclaredFields();

        List<String> actualFields = Arrays.asList(fields).stream().map(Field::getName).collect(Collectors.toList());

        assertEquals(2, actualFields.size());
        assertTrue(actualFields.containsAll(Arrays.asList("name", "CATEGORY")));
    }

}
