package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TransferService {


    public static final String API_BASE_URL = "http://localhost:8080/transfer";

    private RestTemplate restTemplate = new RestTemplate();


    
    public List<Transfer> getTransfers(String authToken) {
        List<Transfer> transfers = null;
        try {
            transfers = restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(authToken), List.class).getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return transfers;
    }



    public Transfer getOneTransfer(String authToken) {
        Transfer transfers = null;
        try {

            ResponseEntity<Transfer> response =
                    restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(authToken), Transfer.class);
            transfers = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }

    public Transfer sendTransfer(String authToken, Transfer sendTransfer){
        Transfer newTransfer = null;
        try {
            newTransfer = restTemplate.exchange(API_BASE_URL, HttpMethod.POST, makeTransferEntity(sendTransfer,authToken), Transfer.class).getBody();
        } catch (RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }
        return newTransfer;
    }




    private HttpEntity<Void> makeAuthEntity(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer, String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transfer, headers);
    }








}
