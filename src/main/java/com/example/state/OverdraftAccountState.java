package com.example.state;

public class OverdraftAccountState extends AbstractAccountState{

    public OverdraftAccountState(NewAccount account) {
        super(account);
    }

    @Override
    protected void withdraw( int amount) {
        System.out.println("开始提现，当前余额："+account.getBalance());
        account.setBalance(account.getBalance()-amount);
        System.out.println("提现完成，当前余额："+account.getBalance());
        refreshState();
    }

    @Override
    protected void refreshState() {
        NewAccount parent = account;
        if(parent.getBalance()<=-2000){
            parent.setState(new RestrictAccountState(parent));
        }
    }
}
