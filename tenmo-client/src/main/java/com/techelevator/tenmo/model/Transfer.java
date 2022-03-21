package com.techelevator.tenmo.model;

public class Transfer {


    private long transferId;
    private long  transferTypeId;
    private long  transferStatusId;
    private long  accountFrom;
    private long  accountTo;
    private double amount;

    public Transfer() {}

    public  Transfer(long transferId, long transferTypeId, long  transferStatusId, long  accountFrom, long  accountTo, double amount) {
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