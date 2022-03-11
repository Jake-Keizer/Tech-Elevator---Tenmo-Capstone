package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.dao.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


//@PreAuthorize("isAuthenticated()")
@RestController
public class TenmoController {
    private final AccountDao accountDao;
    private final TransferDao transferDao;

    public TenmoController(AccountDao accountDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/balance")
    public double checkBalance(Principal user) {
        Account account = accountDao.getAccountByUsername(user.getName());
        return accountDao.checkAccountBalance(account.getUserId());
    }

    @PostMapping(path = "/transfer")
    public void sendTransfer(@RequestBody Transfer transfer, Principal fromUser) {
        Account fromAccount = accountDao.getAccountByUsername(fromUser.getName());
        long fromId = fromAccount.getUserId();
        long id = transfer.getAccountTo();
        double amount = transfer.getAmount();
        System.out.println(transfer.toString());
        double fromAccountBalance = accountDao.checkAccountBalance(fromAccount.getUserId());
        if (fromAccountBalance <= amount) {
            System.out.println("You're too poor to send this much");
        }
        if (fromId == id){
            System.out.println("Can't cheat the system");
        } else {
            transferDao.sendTransfer(fromId,id, amount);
            accountDao.addToBalance(amount,id);
            accountDao.decreaseBalance(amount, fromId);
            System.out.println("transfer complete");
        }

    }



}
