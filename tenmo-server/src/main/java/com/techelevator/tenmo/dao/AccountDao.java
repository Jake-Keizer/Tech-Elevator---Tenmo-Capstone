package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {

    Account getAccountByUsername(String userName);

    Account getAccountById(long accountId);

    double checkAccountBalance(long userId);

    double addToBalance(double deposit, long userId);

    double decreaseBalance(double withdraw, long userId);

    List<Account> listAccounts();






}
