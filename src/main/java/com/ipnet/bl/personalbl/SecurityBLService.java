package com.ipnet.bl.personalbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.PersonalUserDao;

import com.ipnet.entity.PersonalUser;
import com.ipnet.enums.ResultMessage;
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
    public ResultMessage isEmailValidate(String userId) {
        PersonalUser personalUser=userDao.findPersonalUserById(userId);
        if(personalUser.getEmail().equals(null))
            return ResultMessage.Fail;
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage setEmail(String useId, String email) {
        PersonalUser personalUser=userDao.findPersonalUserById(useId);
        if(personalUser.equals(null))
            return ResultMessage.Fail;
        personalUser.setEmail(email);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage isPhoneValidate(String userId) {
        PersonalUser personalUser=userDao.findPersonalUserById(userId);
        if(personalUser.equals(null))
            return ResultMessage.Fail;
        else{
            if (personalUser.getTelephone().equals(null))
                return ResultMessage.Fail;
            else
                return ResultMessage.Success;
        }
    }

    @Override
    public ResultMessage getverification(String phone) {
        if(userDao.existsById(phone)){
            return ResultMessage.Fail;
        }else{
            String code=aliService.sendMessageCode(phone);
            return ResultMessage.Success;
        }

    }

    @Override
    public ResultMessage setPhone(String userId, String phone, String verification) {
        PersonalUser personalUser=userDao.findPersonalUserById(userId);
        personalUser.setTelephone(phone);
        userDao.save(personalUser);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage setPassword(String userId, String password) {
        PersonalUser personalUser=userDao.findPersonalUserById(userId);
        if(personalUser.equals(null))
            return ResultMessage.Fail;
        else{
            personalUser.setPassword(password);
            return ResultMessage.Success;
        }

    }

    @Override
    public ResultMessage setPaymentPassword(String userId, String pay_code) {
        PersonalUser personalUser=userDao.findPersonalUserById(userId);
        if(personalUser.equals(null))
            return ResultMessage.Fail;
        else{
            personalUser.setPay_code(pay_code);
            userDao.save(personalUser);
            return ResultMessage.Success;
        }

    }
}
