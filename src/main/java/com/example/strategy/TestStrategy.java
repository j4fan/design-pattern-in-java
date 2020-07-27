package com.example.strategy;

import java.util.List;
import java.util.stream.Collectors;

public class TestStrategy {

    public static void main(String[] args) {
        BargainExecutor executor = new BargainExecutor(StrategyFactory.createStrategy(StrategyEnum.SIMPLE));
        List<Integer> result = executor.executeBargain(100, 5, 5);
        System.out.println("策略1砍价结果为:" + format(result));


        BargainExecutor executor2 = new BargainExecutor(StrategyFactory.createStrategy(StrategyEnum.WECHAT));
        List<Integer> result2 = executor2.executeBargain(100, 5, 5);
        System.out.println("策略2砍价结果为:" + format(result2));
    }

    private static String format(List<Integer> result) {
        List<String> newList = result.stream().map(i -> i.toString()).collect(Collectors.toList());
        return String.join(",", newList);
    }
}
