package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.dao.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


//@PreAuthorize("isAuthenticated()")
@RestController
public class TenmoController {
    private final AccountDao accountDao;

    public TenmoController(AccountDao accountDao){
        this.accountDao = accountDao;
    }


   @PreAuthorize("hasRole('USER')")
    @GetMapping (path = "/balance")
    public double checkBalance(Principal user) {
       Account account = accountDao.getAccountByUsername(user.getName());
       return accountDao.checkAccountBalance(account.getUserId());
    }



}
