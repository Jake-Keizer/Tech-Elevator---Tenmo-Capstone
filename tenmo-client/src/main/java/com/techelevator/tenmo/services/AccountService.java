package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AccountService {

    public static final String API_BASE_URL = "http://localhost:8080/";

    private RestTemplate restTemplate = new RestTemplate();

            private String authToken = null;


            public void SetAuthToken(String authToken) {this.authToken = authToken;}


            public Account[] getAllAccounts() {
                Account[] accounts = null;

                try {

                    ResponseEntity<Account[]> response =
                            restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), Account[].class);
                    accounts = response.getBody();
                } catch (RestClientResponseException | ResourceAccessException e) {
                    BasicLogger.log(e.getMessage());
                }
                return accounts;
            }



            public Account getOneAccount(int id) {
                Account account = null;
                try {

                    ResponseEntity<Account> response =
                        restTemplate.exchange(API_BASE_URL + id, HttpMethod.GET, makeAuthEntity(), Account.class);
                    account = response.getBody();
                } catch (RestClientResponseException | ResourceAccessException e) {
                     BasicLogger.log(e.getMessage());
                }
                return account;
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


    
    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Account> makeAccountEntity(Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(account, headers);
    }




}










