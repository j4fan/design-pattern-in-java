package com.example.state;

public class NewAccount {


    private AbstractAccountState state;
    private int balance;

    public NewAccount() {
        this.state = new NormalAccountState(this);
        this.balance = 2000;
    }

    public void withdraw(int amount) {
        state.withdraw(amount);
    }

    public AbstractAccountState getState() {
        return state;
    }

    public void setState(AbstractAccountState state) {
        this.state = state;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static void main(String[] args) {
        NewAccount account = new NewAccount();
        account.withdraw(100);
        account.withdraw(2000);
        account.withdraw(2000);
        account.withdraw(2000);
    }

}
