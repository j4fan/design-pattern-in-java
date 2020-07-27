package com.example.strategy;

import java.util.List;

public class BargainExecutor {

    private BargainStrategy strategy;

    public BargainExecutor(BargainStrategy bargainStrategy){
        this.strategy = bargainStrategy;
    }

    public List<Integer> executeBargain(int bargainAmount, int minTime, int maxTime){
        return strategy.bargainExecute(bargainAmount,minTime,maxTime);
    }
}
