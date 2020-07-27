package com.example.strategy;

/**
 * 策略生成工厂方法
 */
public class StrategyFactory {

    public static BargainStrategy createStrategy(StrategyEnum strategyEnum){
        switch (strategyEnum){
            case SIMPLE:
                return new SimpleRandomStrategy();
            case WECHAT:
                return new WechatRandomStrategy();
            default:
                throw new RuntimeException("未实现的策略");
        }
    }
}
