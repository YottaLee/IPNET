package com.ipnet.bl.walletbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.blservice.personalservice.ElectronicWalletBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.UserDao;
import com.ipnet.entity.Person;
import com.ipnet.entity.PersonalUser;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * nan
 */
@Service
public class ElectronicWalletBLServiceImpl implements ElectronicWalletBLService{
    @Autowired
    private Person userDao;
    @Autowired
    private CompanyUserDao companyUserDao;
    @Autowired
    private AliService aliService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CommunityUserBLService communityUserBLService;


    @Override
    public Double getAccountBalance(String userId) {
        return userDao.getOne(userId).getRMB();
    }

    @Override
    public List<String> getAllAccountId(String userId) {
        return userDao.getOne(userId).getBankAccount();
    }

    @Override
    public ResultMessage chargeBalance(String userId, double rmb_num, String card) {
        //???
        List<String> account=userDao.getOne(userId).getBankAccount();

        return ResultMessage.Success;
    }

    @Override
    public ResultMessage withDrawBalance(String userId, double rmb_num, String card) {
        //???
        List<String> account=userDao.searchUserById(userId).getBankAccount();

        return ResultMessage.Success;
    }

    @Override
    public List<CreditCard> getCreditCardInfo(String userId) {

        List<String> account=userDao.getOne(userId).getBankAccount();
        List<CreditCard> creditCards=new ArrayList<>();
        for(String a:account){
            creditCards.add(new CreditCard(a,""));
        }
        return creditCards;
    }

    @Override
    public ResultMessage setCreditCard(String userId, String card, String card_code) {
        PersonalUser personalUser=userDao.getOne(userId);
        if(personalUser.equals(null))
            return ResultMessage.Fail;
        else{
            //密码验证
            if(true){
                personalUser.getBankAccount().add(card);
                userDao.save(personalUser);
                return  ResultMessage.Success;
            }else
                return ResultMessage.Fail;

        }
    }

    @Override
    public ResultMessage cancelCreditCard(String userId, String card) {
        PersonalUser personalUser=userDao.getOne(userId);
        if(personalUser.equals(null))
            return ResultMessage.Fail;
        else{
            //密码验证
            if(true){
                personalUser.getBankAccount().remove(card);
                userDao.save(personalUser);
            }else
                return ResultMessage.Fail;

        }
    }

    @Override
    public int getPoint(String userId) {
        return userDao.getOne(userId).getCredits();
    }
}
