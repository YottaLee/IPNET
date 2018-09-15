package com.ipnet.bl.Accountbl;

/**
 * gy
 */

import com.ipnet.blservice.AccountBLService;
import com.ipnet.blservice.TransactionLogService;
import com.ipnet.dao.AccountDao;
import com.ipnet.entity.contract.Account;
import com.ipnet.utility.IDNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class AccountBLServiceImpl implements AccountBLService {
     @Autowired
     private AccountDao accountDao;

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
            this.transactionLogService.addTransactionLog(srcAcc.getUserId() ,destAcc.getUserId() , srcAcc.getAcountId() , destAcc.getAcountId() , patentId , amount);
        }
        return flag;
    }

    @Override
    public List<Integer> getRMBSum() {
        return null;
    }

    @Override
    public List<Integer> getIPPointsSum() {
        return null;
    }

    @Override
    public List<Double> getProfitSum() {
        return null;
    }
}
