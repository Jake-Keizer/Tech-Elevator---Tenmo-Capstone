package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {


    void transferFunds(long fromUserId, long toUserId, double amount);

    List<Transfer> getTransfersByUserId(long userId);

}
