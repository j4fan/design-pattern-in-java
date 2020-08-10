package com.example.state;

public abstract class AbstractAccountState {

    protected NewAccount account;

    public AbstractAccountState(NewAccount account) {
        this.account = account;
    }

    protected abstract void withdraw(int amount);
    protected abstract void refreshState();
}
