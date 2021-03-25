package com.example.demo.future;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(JUnit4.class)
public class CompletableFutureTest {

    @Test
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "gg";
        });
        System.out.println(future.get());
    }

    @Test
    public void testCompletableFuture2() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000L);
                System.out.println("执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(3000L);
    }

    @Test
    public void testCompletableFutureApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "former";
        });
        CompletableFuture<String> futureAfter = future.thenApply(s -> s + "GG");
        System.out.println(futureAfter.get());
    }

    @Test
    public void testCompletableFutureApplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "former";
        });
        CompletableFuture<String> futureAfter = future.thenApplyAsync(s -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "GG";
        });
        System.out.println(futureAfter.get());
    }

    @Test
    public void testCompletableFutureThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "former";
        });
        CompletableFuture<Void> future1 = future.thenAccept(s -> System.out.println(s));
        future1.get();
    }

    @Test
    public void testThenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (Exception e) {
                e.getMessage();
            }
            return "former";
        });
        CompletableFuture<Void> future1 = future.thenRun(() -> System.out.println("end"));
        future1.get();
    }

    @Test
    public void thenComposeAndThen() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (Exception e) {
                e.getMessage();
            }
            return "former";
        });
        CompletableFuture<String> future1 = future.thenApply(s -> s + "aa");
        CompletableFuture<String> future2 = future.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "bb"));

        System.out.println(future1.get());
        System.out.println(future2.get());
    }

    @Test
    public void testCombineFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello").thenCombine(
                CompletableFuture.supplyAsync(() -> "MM"), (a, b) -> a + b);
        System.out.println(future.get());

        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> "Hello").thenAcceptBoth(
                CompletableFuture.supplyAsync(() -> "MM"), (a, b) -> System.out.println(a + b));
        future1.get();
    }


    @Test
    public void testAllOf() throws ExecutionException, InterruptedException {
        System.out.println("开始执行");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new NewTask("哈哈", 4));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new NewTask("GG", 2));
        CompletableFuture future = CompletableFuture.allOf(future1, future2);
        future.get();
        System.out.println("任务全部结束");
        System.out.println(future1.get()+future2.get());


        String futureStream = Stream.of(future1,future2).map(CompletableFuture::join).collect(Collectors.joining(","));
        System.out.println(futureStream);
    }


    public Future<String> calculateAsync() {
        CompletableFuture future = new CompletableFuture();
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete("gg");
        });
        return future;
    }

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        Future future = calculateAsync();
        System.out.println(future.get());
    }

    @Test
    public void testTask() {

    }

    class NewTask implements Supplier {

        private String name;
        private int sleep;

        public NewTask(String name, int sleep) {
            this.name = name;
            this.sleep = sleep;
        }

        @SneakyThrows
        @Override
        public String get() {
            Thread.sleep(sleep * 1000L);
            return name;
        }
    }

}
