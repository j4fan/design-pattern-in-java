package com.example.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 砍价策略1:每次砍价金额时查看剩余平均金额为p,随机金额为(0,2p)范围内随机
 */
public class SimpleRandomStrategy implements BargainStrategy {

    private Random r = new Random();

    @Override
    public List<Integer> bargainExecute(int bargainAmount, int minTime, int maxTime) {
        int bargainTime = minTime + r.nextInt(maxTime - minTime + 1);
        List<Integer> result = new ArrayList();
        int total = 0;
        int rest = bargainAmount;
        int restTime = bargainTime;
        for (int i = 0; i < bargainTime - 1; i++) {
            int randomAmount = getRandomValOpenOpen(0, 2 * rest / restTime);

            total += randomAmount;
            rest -= randomAmount;
            restTime--;
            result.add(randomAmount);
        }
        result.add(bargainAmount - total);
        return result;
    }

    private int getRandomValOpenOpen(int min, int max) {
        return r.nextInt(max - min - 1) + min + 1;
    }
}
