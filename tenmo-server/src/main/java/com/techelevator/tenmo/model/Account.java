package com.techelevator.tenmo.model;

public class Account {


        private int intId;
        private int userId;
        private double balance;

        public void AccountTable() {}

        public void AccountTable(int intId, int userId, double balance) {
            this.intId = intId;
            this.userId = userId;
            this.balance = balance;


        }

        public void setIntId(int intId) {
            this.intId = intId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }


        public int getIntId() {
            return intId;
        }

        public int getUserId() {
            return userId;
        }

        public double getBalance() {
            return balance;
        }


    }




