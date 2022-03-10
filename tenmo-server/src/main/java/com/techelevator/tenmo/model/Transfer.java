package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class Transfer {


    @JsonProperty("transferID")
    @Min(value = 1, message = "Transfer ID should Be a Positive Number.")
    @NotBlank(message = "This Field ID Is Required.")
    private long transferId;

    @JsonProperty("transferTypeID")
    @Min(value = 1, message = "Transfer ID Should Be a Positive Number.")
    @NotBlank(message = "This Field ID Is Required.")
    private long  transferTypeId;

    @JsonProperty("TransferStatusID")
    private long  transferStatusId;

    @Min(value = 1, message = "Account From Should Be a Positive Number.")
    @NotBlank(message = "The Account Transferring From (user ID) Is Required.")
    private long  accountFrom;

    @Min(value = 1, message = "Account To Should Be a Positive Number.")
    @NotBlank(message = "The Account Transferring To (user ID) Is Required.")
    private long  accountTo;

    @Positive(message = "The Amount Transferring Must Be > 0.")
    @NotBlank(message = "The Account Transferring To Is Required.")
    private double amount;


    public Transfer() {}

    public Transfer(long transferId, long transferTypeId, long  transferStatusId, long  accountFrom, long  accountTo, double amount) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public long  getTransferId() {
        return transferId;
    }

    public long  getTransferTypeId() {
        return transferTypeId;
    }

    public long  getTransferStatusId() {
        return transferStatusId;
    }

    public long  getAccountFrom() {
        return accountFrom;
    }

    public long  getAccountTo() {
        return accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setTransferId(long  transferId) {
        this.transferId = transferId;
    }

    public void setTransferTypeId(long  transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public void setTransferStatusId(long  transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public void setAccountFrom(long  accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(long  accountTo) {
        this.accountTo = accountTo;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }





    @Override
    public String toString() {
        return "TransferStatus{" +
                "transferId=" + transferId +
                "transferTypeId=" + transferTypeId +
                ", transferStatusId='" + transferStatusId +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';

    }
}
