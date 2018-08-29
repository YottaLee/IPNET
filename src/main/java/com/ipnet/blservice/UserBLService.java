package com.ipnet.blservice;

import com.ipnet.enums.ResultMessage;

public interface UserBLService {

    //获取短信验证码，返回发送的验证码；若手机号已被注册，返回"Exist"
    String getMessageCode(String username);

    //获取邮件验证
    String sendEmail(String toEmail);
    void sendHtmlMail(String toEmail);

    //个人用户注册，可以用手机号或者邮箱
    ResultMessage personalRegister(String username, String password);

    //企业用户注册，使用企业邮箱
    ResultMessage companyRegister(String email, String password);

    //个人用户登录,可以使用手机号或者邮箱
    ResultMessage login(String username, String password);

    //用户登出
    ResultMessage logout(String username);
}
