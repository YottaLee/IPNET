package com.ipnet.bl.Accountbl;

import com.ipnet.blservice.AccountBLService;
import com.ipnet.dao.AccountDao;
import com.ipnet.entity.contract.Account;
import com.ipnet.utility.IDNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountBLServiceImpl implements AccountBLService {
     @Autowired
     private AccountDao accountDao;

    @Override
    public boolean MoneyMove(String srcAccount, String destAccount, double amount) throws IDNotExistsException {
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
        }
        return flag;
    }
}
