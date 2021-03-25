package com.example.demo.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(JUnit4.class)
public class GuavaCacheTest {

    @Test
    public void testCacheConcurrent() throws InterruptedException, ExecutionException {
        Cache<String, List<String>> textCache = CacheBuilder.newBuilder().maximumSize(10).build();
        List<String> strings = textCache.getIfPresent("123");
        textCache.put("123", Lists.newArrayList("11","22"));
        System.out.println(strings.size());
    }
}
