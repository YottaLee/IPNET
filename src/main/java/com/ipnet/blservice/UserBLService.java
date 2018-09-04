package com.ipnet.blservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.uservo.EmailRegister;

import java.util.Map;

public interface UserBLService {

    //个人用户手机验证码注册
    //获取短信验证码，返回发送的验证码；若手机号已被注册，返回"Exist"
    Map<String,String> getMessageCode(String telephone);
    //个人用户手机号注册
    ResultMessage registerByPhone(Map<String,String> request);

    //个人用户邮箱注册
    ResultMessage personalEmailRegister(EmailRegister register);
    //企业用户邮箱注册
    ResultMessage companyRegister(EmailRegister register);
    //邮箱验证
    ResultMessage checkEmail(String email,String code);
    /*
    * 用户登录
    * 方式：手机号登录——仅限个人
    *       邮箱登录——个人或企业*/

    //手机号登录，仅限个人用户
    ResultMessage loginPhone(String telephone, String password);

    //邮箱登录,企业和个人都可以
    ResultMessage loginEmail(String email, String password);

    //获取用户的头像的url
    String getImageUrl(String username);
}
