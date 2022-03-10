package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Account {

        @JsonProperty("accountID")
        @NotBlank(message = "This field ID is required.")
        private long accountId;

        @JsonProperty("userID")
        private long userId;

        @Positive(message = "The Account Balance Must Be > 0.")
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




