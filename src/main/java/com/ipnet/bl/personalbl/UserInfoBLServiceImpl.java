package com.ipnet.bl.personalbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.blservice.personalservice.UserInfoBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.UserDao;
import com.ipnet.entity.CompanyUser;
import com.ipnet.entity.PersonalUser;
import com.ipnet.enums.ResultMessage;
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
    private UserDao userDao;
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
        if(userDao.searchUserById(personalUserSaveVo.getUsername()).equals(null)){
            return ResultMessage.Fail;
        }else{
            PersonalUser personalUser=userDao.searchUserById(personalUserSaveVo.getUsername());
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
        if(companyUserDao.searchUserById(companyUserSaveVo.getName()).equals(null)){
            return ResultMessage.Fail;
        }else{
            CompanyUser companyUser=companyUserDao.searchUserById(companyUserSaveVo.getName());
            companyUser.setJuridical_person(companyUserSaveVo.getRepresentative());



            companyUserDao.save(companyUser);
            return ResultMessage.Success;
        }

    }

    @Override
    public UserInfoVo getUserInfo(String userId) {
        PersonalUser personalUser=userDao.searchUserById(userId);
        if(personalUser!=null){
            return new UserInfoVo(personalUser.getName(),personalUser.getSex(),personalUser.getTelephone(),
                    personalUser.getIndustry(),personalUser.getCompany(),personalUser.getRegion(),personalUser.getDescription(),
                    personalUser.getIdPhoto());
        }
        return null;
    }

    @Override
    public AccountInfoVo getAccountInfo(String userId) {
        PersonalUser personalUser=userDao.searchUserById(userId);
        if(personalUser.equals(null))
            return null;
        else{
            return new AccountInfoVo();
        }

    }

    @Override
    public ResultMessage isUserValidate(String userId) {
        PersonalUser personalUser=userDao.searchUserById(userId);
        if(personalUser.getIdentities().size()!=0)
            return ResultMessage.Success;
        return ResultMessage.Fail;
    }
}
