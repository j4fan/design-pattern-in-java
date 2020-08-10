package com.example.state;

/**
 * 未经状态模式改造的
 */
public class OriginAccount {

    public OriginAccount(StateTypeEnum stateTypeEnum, int balance) {
        this.stateTypeEnum = stateTypeEnum;
        this.balance = balance;
    }

    private StateTypeEnum stateTypeEnum;

    private int balance;

    private void withdraw(int amount){
        switch (stateTypeEnum){
            case NORMAL:
                System.out.println("发起提现，当前余额："+balance);
                balance -=amount;
                System.out.println("提现成功，当前余额："+balance);
                break;
            case OVERDRAFT:
                System.out.println("发起提现，当前余额："+balance);
                balance -=amount;
                System.out.println("提现成功，当前余额："+balance);
                break;
            case RESTRICT:
                System.out.println("账号冻结，无法提现");
        }
        refreshState();
    }

    private void refreshState(){
        if(balance>=0){
            stateTypeEnum = StateTypeEnum.NORMAL;
        }else if(balance>-2000){
            stateTypeEnum = StateTypeEnum.OVERDRAFT;
        }else {
            stateTypeEnum = StateTypeEnum.RESTRICT;
        }
    }

    public static void main(String[] args) {
        OriginAccount originAccount = new OriginAccount(StateTypeEnum.NORMAL,2000);
        originAccount.withdraw(100);
        originAccount.withdraw(2000);
        originAccount.withdraw(2000);
        originAccount.withdraw(2000);
    }
}
