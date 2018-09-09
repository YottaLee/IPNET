package com.ipnet.controller.personalinfocontroller;

import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.personalservice.UserInfoBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.uservo.AccountInfoVo;
import com.ipnet.vo.uservo.CompanyUserSaveVo;
import com.ipnet.vo.uservo.PersonalUserSaveVo;
import com.ipnet.vo.uservo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoBLService userBLService;

    //修改个人用户
    @RequestMapping("/modifyPerson")
    public @ResponseBody
    ResultMessage savePersonalUserInfo(PersonalUserSaveVo personalUserSaveVo){
        return userBLService.savePersonalUserInfo(personalUserSaveVo);
    }

    //修改企业用户
    @RequestMapping("/modifyCompany")
    public @ResponseBody
    ResultMessage saveCompanyUserInfo(CompanyUserSaveVo companyUserSaveVo){
        return userBLService.saveCompanyUserInfo(companyUserSaveVo);
    }


    //身份信息查看
    @RequestMapping("/getUser")
    public @ResponseBody
    UserInfoVo getUserInfo(String userid){
        return userBLService.getUserInfo(userid);
    }

    //账户信息查看
    @RequestMapping("/getAccount")
    public @ResponseBody
    AccountInfoVo getAccountInfo (String userId){
        return userBLService.getAccountInfo(userId);
    }

    //查看是否完成验证
    @RequestMapping("/getAccount")
    public @ResponseBody
    ResultMessage isUserValidate (String userId){
        return userBLService.isUserValidate(userId);
    }


}
