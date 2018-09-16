package com.ipnet.blservice;

import com.ipnet.entity.Transaction;

import java.util.List;

public interface TransactionLogService {
    void addTransactionLog(String buyer , String seller , String buyer_bank_account , String seller_bank_account , String patentId , double amount , int IPPoint);
    List<Transaction> getAllTransactions();
}
