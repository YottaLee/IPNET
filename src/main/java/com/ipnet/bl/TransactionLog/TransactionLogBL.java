package com.ipnet.bl.TransactionLog;

import com.ipnet.blservice.TransactionLogService;
import com.ipnet.dao.TransactionDao;
import com.ipnet.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TransactionLogBL implements TransactionLogService{
    @Autowired
    private  TransactionDao transactionDao;

    @Override
    public void addTransactionLog(String buyer, String seller, String buyer_bank_account, String seller_bank_account, String patentId , double amount ,int IPPoint) {
        Transaction transaction = new Transaction();
        transaction.setBuyer(buyer);
        transaction.setSeller(seller);
        transaction.setBuyer_bank_account(buyer_bank_account);
        transaction.setSeller_bank_account(seller_bank_account);
        transaction.setPatent_id(patentId);
        transaction.setAmount(amount);
        transaction.setIPPoint(IPPoint);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = df.format(new Date());
        transaction.setTranscation_time(date);
        this.transactionDao.saveAndFlush(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = this.transactionDao.findAll();
        if(transactions.size() == 0 || transactions ==null){
            return null;
        }
        return transactions;
    }
}
