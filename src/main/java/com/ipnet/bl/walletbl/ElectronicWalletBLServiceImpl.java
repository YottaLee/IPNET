package com.ipnet.bl.walletbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.blservice.personalservice.ElectronicWalletBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.PersonalUserDao;

import com.ipnet.entity.CompanyUser;
import com.ipnet.entity.PersonalUser;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.UserType;
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
    private PersonalUserDao userDao;
    @Autowired
    private CompanyUserDao companyUserDao;
    @Autowired
    private AliService aliService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CommunityUserBLService communityUserBLService;


    @Override
    public Double getAccountBalance(String userId,UserType userType) {
        switch (userType){
            case Personal:
                return userDao.findPersonalUserById(userId).getRMB();
            case Company:
                return companyUserDao.findCompanyUserById(userId).getMoney();
        }
        return null;
    }

    @Override
    public List<String> getAllAccountId(String userId,UserType userType) {
        switch (userType){
            case Personal:
                return userDao.findPersonalUserById(userId).getBankAccount();
            case Company:
                return companyUserDao.findCompanyUserById(userId).getBank_accounts();
        }
        return null;
    }

    @Override
    public ResultMessage chargeBalance(String userId, double rmb_num, String card) {
        //???
        List<String> account=userDao.findPersonalUserById(userId).getBankAccount();

        return ResultMessage.Success;
    }

    @Override
    public ResultMessage withDrawBalance(String userId, double rmb_num, String card) {
        //???
        List<String> account=userDao.findPersonalUserById(userId).getBankAccount();

        return ResultMessage.Success;
    }

    @Override
    public List<CreditCard> getCreditCardInfo(String userId,UserType userType) {
        switch (userType){
            case Company:
                List<String> account1=companyUserDao.findCompanyUserById(userId).getBank_accounts();
                List<CreditCard> creditCards1=new ArrayList<>();
                for(String a:account1){
                    creditCards1.add(new CreditCard(a,""));
                }
                return creditCards1;
            case Personal:
                List<String> account=userDao.findPersonalUserById(userId).getBankAccount();
                List<CreditCard> creditCards=new ArrayList<>();
                for(String a:account){
                    creditCards.add(new CreditCard(a,""));
                }
                return creditCards;
        }
        return null;

    }

    @Override
    public ResultMessage setCreditCard(String userId, String card, String card_code,String bank,UserType userType) {
        switch (userType){
            case Company:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    //密码验证
                    if(true){
                        companyUser.getBank_accounts().add(card);
                        companyUserDao.save(companyUser);
                        return  ResultMessage.Success;
                    }else
                        return ResultMessage.Fail;

                }
            case Personal:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
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
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage cancelCreditCard(String userId, String card,UserType userType) {
        switch (userType){
            case Company:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    //密码验证
                    if(true){
                        companyUser.getBank_accounts().remove(card);
                        companyUserDao.save(companyUser);
                        return ResultMessage.Success;
                    }else
                        return ResultMessage.Fail;

                }
            case Personal:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    //密码验证
                    if(true){
                        personalUser.getBankAccount().remove(card);
                        userDao.save(personalUser);
                        return ResultMessage.Success;
                    }else
                        return ResultMessage.Fail;

                }

        }
        return ResultMessage.Fail;
    }

    @Override
    public int getPoint(String userId,UserType userType) {
        switch (userType){
            case Company:
                return companyUserDao.findCompanyUserById(userId).getPoints();
            case Personal:
                return userDao.findPersonalUserById(userId).getCredits();
        }
        return 0;
    }

    @Override
    public ResultMessage updatePoint(String userId,int point,UserType userType) {
        switch (userType){
            case Company:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                companyUser.setPoints(companyUser.getPoints()+point);
                companyUserDao.save(companyUser);
                return ResultMessage.Success;
            case Personal:
               PersonalUser personalUser=userDao.findPersonalUserById(userId);
               personalUser.setCredits(personalUser.getCredits()+point);
               userDao.save(personalUser);
               return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }
}
