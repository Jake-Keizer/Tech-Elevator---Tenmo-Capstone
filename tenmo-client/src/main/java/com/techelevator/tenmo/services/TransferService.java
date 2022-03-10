package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferService {


    public static final String API_BASE_URL = "http://localhost:8080/";

    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;


    public void SetAuthToken(String authToken) {this.authToken = authToken;}

    
    public Transfer[] getAllTransfers() {
        Transfer[] transfers = null;

        try {

            ResponseEntity<Transfer[]> response =
                    restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            transfers = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }



    public Transfer getOneTransfer(int id) {
        Transfer transfers = null;
        try {

            ResponseEntity<Transfer> response =
                    restTemplate.exchange(API_BASE_URL + id, HttpMethod.GET, makeAuthEntity(), Transfer.class);
            transfers = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }



    public boolean updateTransfer(Transfer updatedTransfer) {
        HttpEntity<Transfer> entity = makeTransferEntity(updatedTransfer);

        boolean success = false;
        try {
            restTemplate.put(API_BASE_URL + updatedTransfer.getTransferId(), entity);
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

    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transfer, headers);
    }








}
