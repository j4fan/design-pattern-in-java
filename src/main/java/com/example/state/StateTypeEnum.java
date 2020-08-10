package com.example.state;

public enum  StateTypeEnum {

    /**
     * 余额>=0 正常
     */
    NORMAL,
    /**
     * 余额>-2000&<0 透支
     */
    OVERDRAFT,
    /**
     * 余额<=-2000 冻结
     */
    RESTRICT;

}
