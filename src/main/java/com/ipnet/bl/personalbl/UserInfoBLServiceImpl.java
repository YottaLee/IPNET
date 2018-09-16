package com.ipnet.bl.personalbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.blservice.personalservice.UserInfoBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.PersonalUserDao;
import com.ipnet.entity.CompanyUser;
import com.ipnet.entity.PersonalUser;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.enums.Sex;
import com.ipnet.enums.UserType;
import com.ipnet.vo.uservo.AccountInfoVo;
import com.ipnet.vo.uservo.CompanyUserSaveVo;
import com.ipnet.vo.uservo.PersonalUserSaveVo;
import com.ipnet.vo.uservo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * by nan
 */
@Service
public class UserInfoBLServiceImpl implements UserInfoBLService {
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
    public ResultMessage savePersonalUserInfo(PersonalUserSaveVo personalUserSaveVo) {
        if(userDao.findPersonalUserByName(personalUserSaveVo.getUsername()).equals(null)){
            return ResultMessage.Fail;
        }else{
            PersonalUser personalUser=userDao.findPersonalUserByName(personalUserSaveVo.getUsername());
            personalUser.setName(personalUserSaveVo.getName());
            personalUser.setSex(personalUserSaveVo.getGender());
            personalUser.setTelephone(personalUserSaveVo.getPhone());
            personalUser.setIndustry(personalUserSaveVo.getProfession());
            personalUser.setCompany(personalUserSaveVo.getCompany());
            personalUser.setRegion(personalUserSaveVo.getRegion());
            personalUser.setDescription(personalUserSaveVo.getStatement());
            personalUser.setIdPhoto(personalUserSaveVo.getIDcard_img());
            userDao.save(personalUser);
            return ResultMessage.Success;
        }

    }

    @Override
    public ResultMessage saveCompanyUserInfo(CompanyUserSaveVo companyUserSaveVo) {
        if(companyUserDao.findCompanyUserByName(companyUserSaveVo.getName()).equals(null)){
            return ResultMessage.Fail;
        }else{
            CompanyUser companyUser=companyUserDao.findCompanyUserByName(companyUserSaveVo.getName());
            companyUser.setRepresentative(companyUserSaveVo.getRepresentative());
            
            companyUserDao.save(companyUser);
            return ResultMessage.Success;
        }

    }

    @Override
    public UserInfoVo getUserInfo(String userId,Role userType) {
        switch(userType){
            case PersonalUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser!=null)
                    return new UserInfoVo(companyUser.getName(), null ,companyUser.getTel(),
                            null,companyUser.getName(),companyUser.getAddress(),companyUser.getDescription()
                    ,companyUser.getLicence());
            case CompanyUser:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser!=null){
                    return new UserInfoVo(personalUser.getName(),personalUser.getSex(),personalUser.getTelephone(),
                            personalUser.getIndustry(),personalUser.getCompany(),personalUser.getRegion(),personalUser.getDescription(),
                            personalUser.getIdPhoto());
                }
        }

        return null;
    }

    @Override
    public AccountInfoVo getAccountInfo(String userId,Role userType) {
        switch(userType){
            case CompanyUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.equals(null))
                    return null;
                else{
                    return new AccountInfoVo(companyUser.getBank_accounts(),companyUser.getId(),companyUser.getMoney());
                }
            case PersonalUser:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser.equals(null))
                    return null;
                else{
                    return new AccountInfoVo(personalUser.getBankAccount(),personalUser.getId(),personalUser.getRMB());
                }
        }
        return null;

    }

    @Override
    public ResultMessage isUserValidate(String userId,Role userType) {
        switch(userType){
            case CompanyUser:
                CompanyUser companyUser=companyUserDao.findCompanyUserById(userId);
                if(companyUser.getIdentities().size()!=0)
                    return ResultMessage.Success;
                return ResultMessage.Fail;
            case PersonalUser:
                PersonalUser personalUser=userDao.findPersonalUserById(userId);
                if(personalUser.getIdentities().size()!=0)
                    return ResultMessage.Success;
                return ResultMessage.Fail;
        }
        return null;
    }
}
