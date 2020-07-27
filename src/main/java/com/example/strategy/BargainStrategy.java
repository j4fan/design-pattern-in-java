package com.example.strategy;

import java.util.List;

/**
 * 砍价策略接口
 */
public interface BargainStrategy {

    List<Integer> bargainExecute(int bargainAmount,int minTime,int maxTime);
}
