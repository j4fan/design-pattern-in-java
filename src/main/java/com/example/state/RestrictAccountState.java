package com.example.state;

public class RestrictAccountState extends AbstractAccountState{
    public RestrictAccountState(NewAccount account) {
        super(account);
    }

    @Override
    protected void withdraw(int amount) {
        System.out.println("当前账号冻结，无法提现");
    }

    @Override
    protected void refreshState( ) {

    }
}
