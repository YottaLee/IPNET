package com.ipnet.bl.Accountbl;

/**
 * gy
 */

import com.ipnet.blservice.AccountBLService;
import com.ipnet.blservice.TransactionLogService;
import com.ipnet.blservice.personalservice.ElectronicWalletBLService;
import com.ipnet.dao.AccountDao;
import com.ipnet.entity.Transaction;
import com.ipnet.entity.contract.Account;
import com.ipnet.utility.IDNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.ipnet.enums.Role.Financial;
import static com.ipnet.enums.Role.Insurance;
import static com.ipnet.enums.Role.PersonalUser;


@Service
public class AccountBLServiceImpl implements AccountBLService {
     @Autowired
     private AccountDao accountDao;

    @Autowired
    private ElectronicWalletBLService service;

     @Autowired
     private TransactionLogService transactionLogService;

     @Override
    public boolean MoneyMove(String srcAccount, String destAccount, double amount ,String paytype , String patentId) throws IDNotExistsException {
        boolean flag = false;
        Optional<Account> srcaccountOptional = this.accountDao.findById(srcAccount);
        Optional<Account> destaccountOptional = this.accountDao.findById(destAccount);
        if(srcaccountOptional.isPresent() == false || srcaccountOptional == null){
            throw new IDNotExistsException("srcAccountId not exists");
        }
        if(destaccountOptional.isPresent() == false || destaccountOptional == null){
            throw new IDNotExistsException("destAccountId not exists");
        }
        Account srcAcc = srcaccountOptional.get();
        Account destAcc = destaccountOptional.get();
        if(srcAcc.getBalance()>=amount) {
            flag = true;
            double srcAmount = srcAcc.getBalance();
            double desAmount = destAcc.getBalance();
            srcAcc.setBalance(srcAmount-amount);
            destAcc.setBalance(desAmount+amount);
            this.accountDao.saveAndFlush(srcAcc);
            this.accountDao.saveAndFlush(destAcc);
            if(paytype.equals("license")) {
                  //修改个人属性
                  //生成日志
                  //可能需要调用对应的具体方法

            }
            if(paytype.equals("transfer")) {
                service.updatePoint(srcAcc.getUserId() , 20 , PersonalUser);
                this.transactionLogService.addTransactionLog(srcAcc.getUserId() ,destAcc.getUserId() , srcAcc.getAcountId() , destAcc.getAcountId() , patentId , amount , 20);
            }
            if(paytype.equals("evaluation")) {

            }
            if(paytype.equals("loan")) {
                service.updatePoint(srcAcc.getUserId() , 10 , Financial);
                this.transactionLogService.addTransactionLog(srcAcc.getUserId() ,destAcc.getUserId() , srcAcc.getAcountId() , destAcc.getAcountId() , patentId , amount , 2);
            }
            if(paytype.equals("insurance")) {
                service.updatePoint(destAcc.getUserId() , 2 , Insurance);
                this.transactionLogService.addTransactionLog(srcAcc.getUserId() ,destAcc.getUserId() , srcAcc.getAcountId() , destAcc.getAcountId() , patentId , amount , 2);
            }
            if(paytype.equals("claim")) {

            }
            this.transactionLogService.addTransactionLog(srcAcc.getUserId() ,destAcc.getUserId() , srcAcc.getAcountId() , destAcc.getAcountId() , patentId , amount , 10);
        }
        return flag;
    }

    @Override
    public void addAccount(String accountId, double amount, String userId) {
         System.out.println("addAccount!!!!!!!!!!!:"+accountId+" "+amount+" "+userId);
         Account account = new Account();
         account.setAcountId(accountId);
         account.setBalance(amount);
         account.setUserId(userId);
         accountDao.saveAndFlush(account);
    }

    @Override
    public List<Integer> getRMBSum() {
         List<Transaction> transactions = this.transactionLogService.getAllTransactions();
         List<Integer> Sum = new ArrayList<Integer>();
         Date current  = new Date();
         SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
         String now = df.format(current);
         int[] result = {0,0,0,0,0,0};
         for(Transaction transaction : transactions){
              if(isInpriod(now,transaction.getTranscation_time())){
                     result[getIndex(now,transaction.getTranscation_time())] += transaction.getAmount();
              }
         }
         for(int i = 0;i < 6;i++){
             Sum.add(result[i]);
         }
        return Sum;
    }

    @Override
    public List<Integer> getIPPointsSum() {
        List<Transaction> transactions = this.transactionLogService.getAllTransactions();
        List<Integer> Sum = new ArrayList<Integer>();
        Date current  = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = df.format(current);
        int[] result = {0,0,0,0,0,0};
        for(Transaction transaction : transactions){
            if(isInpriod(now,transaction.getTranscation_time())){
                result[getIndex(now,transaction.getTranscation_time())] += transaction.getIPPoint();
            }
        }
        for(int i = 0;i < 6;i++){
            Sum.add(result[i]);
        }
        return Sum;
    }

    @Override
    public List<Double> getProfitSum() {
        List<Transaction> transactions = this.transactionLogService.getAllTransactions();
        return null;
    }

    public static boolean isInpriod(String currentDate , String targetDate){
          boolean flag = false;
          if((currentDate.substring(0,4).equals(targetDate.substring(0,4)))&&(Integer.parseInt(currentDate.substring(4,6))-Integer.parseInt(targetDate.substring(4,6))) <= 5){
              flag = true;
          }
          if((Integer.parseInt(currentDate.substring(0,4))-Integer.parseInt(targetDate.substring(0,4))== 1) && (Integer.parseInt(currentDate.substring(4,6))+12-Integer.parseInt(targetDate.substring(4,6))) <= 5) {
              flag = true;
         }

          return flag;
    }

    public static int getIndex(String currentDate , String targetDate) {
         if(currentDate.substring(0,4).equals(targetDate.substring(0,4))){
             return (Integer.parseInt(currentDate.substring(0,6))-Integer.parseInt(targetDate.substring(0,6)));
        }
         return (Integer.parseInt(currentDate.substring(4,6))+12-Integer.parseInt(targetDate.substring(4,6)));
    }

    public String getUserId(String accountId) {
         Account account = this.accountDao.findById(accountId).get();
         return account.getUserId();
    }
    public static void  main(String args[]){
         System.out.println(isInpriod("2018011522","2017081922"));
        System.out.println(getIndex("2018011522","2017081922"));
    }
}
