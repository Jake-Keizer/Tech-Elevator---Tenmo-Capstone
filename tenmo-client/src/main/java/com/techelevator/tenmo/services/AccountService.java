package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AccountService {

    private  String API_BASE_URL = "http://localhost:8080/";
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    public double checkAccountBalance(String authToken) {
        double balance = 0;
        try {
             balance = restTemplate.exchange(API_BASE_URL + "balance" , HttpMethod.GET, makeAuthEntity(authToken), double.class).getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return balance;
    }

    public List<Account> listAccounts(String authToken){
        List<Account> allAccounts = null;
        try {
            allAccounts = restTemplate.exchange(API_BASE_URL + "users", HttpMethod.GET, makeAuthEntity(authToken), List.class).getBody();
        } catch (ResourceAccessException | RestClientResponseException e) {
            System.out.println(e.getMessage());
        }
        return allAccounts;
    }

    private HttpEntity makeAuthEntity(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    private HttpEntity makeAccountEntity(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }


}










