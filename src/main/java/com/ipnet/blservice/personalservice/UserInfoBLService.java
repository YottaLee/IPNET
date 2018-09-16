package com.ipnet.blservice.personalservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.enums.UserType;
import com.ipnet.vo.uservo.AccountInfoVo;
import com.ipnet.vo.uservo.CompanyUserSaveVo;
import com.ipnet.vo.uservo.PersonalUserSaveVo;
import com.ipnet.vo.uservo.UserInfoVo;

/**
 *  by nan
 */
public interface UserInfoBLService {
    //修改个人用户
    ResultMessage savePersonalUserInfo(PersonalUserSaveVo personalUserSaveVo);
    //修改企业用户
    ResultMessage saveCompanyUserInfo(CompanyUserSaveVo companyUserSaveVo);
    //身份信息查看
    UserInfoVo getUserInfo(String userId, Role userType);
    //账户信息查看
    AccountInfoVo getAccountInfo (String userId,Role userType);
    //查看是否完成验证
    ResultMessage isUserValidate (String userId,Role userType);
}
