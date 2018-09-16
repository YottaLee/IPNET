package com.ipnet.bl.personalbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.PersonalUserDao;

import com.ipnet.entity.CompanyUser;
import com.ipnet.entity.PersonalUser;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;

/**
 *  by nan
 */
@Service
public class SecurityBLService implements com.ipnet.blservice.personalservice.SecurityBLService {
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

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public ResultMessage setUsername(String userId, String username) {

        PersonalUser personalUser=userDao.findPersonalUserById(userId);
        if(personalUser.equals(null)){
            return ResultMessage.Fail;
        }else{
            personalUser.setName(username);
            return ResultMessage.Success;
        }
    }

    @Override
    public ResultMessage isEmailValidate(String userId,Role userType) {
        switch (userType){
            case CompanyUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.getEmail().equals(null))
                    return ResultMessage.Fail;
                return ResultMessage.Success;
            case PersonalUser:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser.getEmail().equals(null))
                    return ResultMessage.Fail;
                return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage setEmail(String useId, String email,Role userType) {
        switch (userType){
            case PersonalUser:
                PersonalUser personalUser=userDao.findPersonalUserById(useId);
                if(personalUser.equals(null))
                    return ResultMessage.Fail;
                personalUser.setEmail(email);
                userDao.save(personalUser);
                return ResultMessage.Success;
            case CompanyUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(useId);
                if(companyUser.equals(null))
                    return ResultMessage.Fail;
                companyUser.setEmail(email);
                companyUserDao.save(companyUser);
                return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage isPhoneValidate(String userId,Role userType) {
        switch (userType){
            case PersonalUser:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    if (personalUser.getTelephone().equals(null))
                        return ResultMessage.Fail;
                    else
                        return ResultMessage.Success;
                }
            case CompanyUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    if (companyUser.getTel().equals(null))
                        return ResultMessage.Fail;
                    else
                        return ResultMessage.Success;
                }
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage getverification(String phone,String userId,Role userType) {
        switch (userType){
            case PersonalUser:
                if(userDao.existsById(phone)){
                    return ResultMessage.Fail;
                }else{
                    PersonalUser personalUser=userDao.findPersonalUserById(userId);
                    String code=aliService.sendMessageCode(phone);
                    personalUser.setVertification(code);
                    userDao.save(personalUser);
                    return ResultMessage.Success;
                }
            case CompanyUser:
                if(companyUserDao.existsById(phone)){
                    return ResultMessage.Fail;
                }else{
                    CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                    String code=aliService.sendMessageCode(phone);
                    companyUser.setVertification(code);
                    companyUserDao.save(companyUser);
                    return ResultMessage.Success;
                }
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage setPhone(String userId, String phone, String verification,Role userType) {
        switch (userType){
            case PersonalUser:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser.getVertification().equals(verification)){
                    personalUser.setTelephone(phone);
                    userDao.save(personalUser);
                    return ResultMessage.Success;
                }
               return ResultMessage.Fail;
            case CompanyUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.getVertification().equals(verification)){
                    companyUser.setTel(phone);
                    companyUserDao.save(companyUser);
                    return ResultMessage.Success;
                }
                return ResultMessage.Fail;
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage setPassword(String userId, String password,Role userType) {
        switch (userType){
            case PersonalUser:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    personalUser.setPassword(password);
                    userDao.save(personalUser);
                    return ResultMessage.Success;
                }
            case CompanyUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    companyUser.setPassword(password);
                    companyUserDao.save(companyUser);
                    return ResultMessage.Success;
                }

        }
        return  ResultMessage.Fail;

    }

    @Override
    public ResultMessage setPaymentPassword(String userId, String pay_code,Role userType) {
        switch (userType){
            case PersonalUser:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    personalUser.setPay_code(pay_code);
                    userDao.save(personalUser);
                    return ResultMessage.Success;
                }
            case CompanyUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.equals(null))
                    return ResultMessage.Fail;
                else{
                    companyUser.setPay_code(pay_code);
                    companyUserDao.save(companyUser);
                    return ResultMessage.Success;
                }
        }
        return ResultMessage.Fail;

    }
}
