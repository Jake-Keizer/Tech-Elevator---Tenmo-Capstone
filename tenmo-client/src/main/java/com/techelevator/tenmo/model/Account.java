package com.techelevator.tenmo.model;

public class Account {
    private long accountId;
    private long userId;
    private double balance;

    public void Account() {}

    public void Account(long accountId, long userId, double balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;


    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public long getAccountId() {
        return accountId;
    }

    public long getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userId ='" + userId +
                ", balance ='" + balance +
                '}';

    }

}
