package com.example.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Pigeon implements Bird {
    @Override
    public void fly() {
        System.out.println("信鸽飞行");
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Bird bird = new Pigeon();
        Bird proxy = (Bird) Proxy.newProxyInstance(bird.getClass().getClassLoader(), bird.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(proxy.getClass().getName());
                System.out.println("唱歌");
                method.invoke(bird, args);
                return null;
            }
        });
        System.out.println("------");
        proxy.fly();
        System.out.println("------");
    }
}
