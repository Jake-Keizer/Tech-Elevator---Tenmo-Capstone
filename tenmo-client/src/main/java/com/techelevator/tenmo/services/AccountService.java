package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AccountService {

    public static final String API_BASE_URL = "http://localhost:8080/";

    private RestTemplate restTemplate = new RestTemplate();

            private String authToken = null;


            public void SetAuthToken(String authToken) {this.authToken = authToken;}


            public Account[]  getAllAccounts(AuthenticatedUser currentUser) {
                Account[] accounts = null;

                try {

                    ResponseEntity<Account[]> response =
                            restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(currentUser), Account[].class);
                    accounts = response.getBody();
                } catch (RestClientResponseException | ResourceAccessException e) {
                    BasicLogger.log(e.getMessage());
                }
                return accounts;
            }


    public double checkAccountBalance(AuthenticatedUser currentUser) {
        double balance = 0;
        Account account = new Account();
        HttpEntity<Account> entity = makeAccountEntity(account);
        try {
            entity = restTemplate.exchange(API_BASE_URL + "balance", HttpMethod.GET, makeAuthEntity(currentUser), Account.class);
            balance = entity.getBody().getBalance();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;


    }




            public boolean updateAccount(Account updatedAccount) {
             HttpEntity<Account> entity = makeAccountEntity(updatedAccount);

            boolean success = false;
            try {
                restTemplate.put(API_BASE_URL + updatedAccount.getAccountId(), entity);
                success = true;
            } catch (RestClientResponseException | ResourceAccessException e) {
                BasicLogger.log(e.getMessage());
            }
            return success;
    }


    
    private HttpEntity<AuthenticatedUser> makeAuthEntity(AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(currentUser, headers);
    }

    private HttpEntity<Account> makeAccountEntity(Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(account, headers);
    }




}










