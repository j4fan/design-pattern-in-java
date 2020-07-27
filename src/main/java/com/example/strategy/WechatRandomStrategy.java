package com.example.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 类似微信砍价策略，将总金额看做一根线段，砍价N次则在线段上找到不重复的N-1个点，每个点间隔形成的线段即为每次随机砍价金额
 */
public class WechatRandomStrategy implements BargainStrategy {

    private Random r = new Random();

    @Override
    public List<Integer> bargainExecute(int bargainAmount, int minTime, int maxTime) {
        int bargainTime = getRandomValCloseClose(minTime, maxTime);
        List<Integer> boards = new ArrayList();
        boards.add(0);
        boards.add(bargainAmount);
        //红包个数和板砖个数的关系
        while (boards.size() <= bargainTime) {
            int index = getRandomValOpenOpen(0, bargainAmount);
            if (boards.contains(index)) {
                //保证板子的位置不相同
                continue;
            }
            boards.add(index);
        }
        //计算每个红包的金额，将两个板子之间的钱加起来
        Collections.sort(boards);
        List<Integer> list = new ArrayList();
        for (int i = 0; i < boards.size() - 1; i++) {
            Integer e = boards.get(i + 1) - boards.get(i);
            list.add(e);
        }
        return list;
    }

    private int getRandomValCloseClose(int min, int max) {
        return r.nextInt(max - min + 1) + min;
    }

    private int getRandomValOpenOpen(int min, int max) {
        return r.nextInt(max - min - 1) + min + 1;
    }
}
