package com.ipnet.blservice;

public interface TransactionLogService {
    void addTransactionLog(String buyer , String seller , String buyer_bank_account , String seller_bank_account , String patentId , double amount);
}
