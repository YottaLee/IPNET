package com.ipnet.blservice.personalservice;

import com.ipnet.enums.ResultMessage;

/**
 * by nan
 */
public interface SecurityBLService {

    //用户名设置
    ResultMessage setUsername(String userId,String username);
    //查看是否已绑定邮箱
    ResultMessage isEmailValidate(String userId);
    //绑定邮箱
    ResultMessage setEmail(String useId,String email);
    //查看是否已绑定手机号
    ResultMessage isPhoneValidate(String userId);
    //获取验证码
    ResultMessage getverification(String phone);
    //绑定手机号
    ResultMessage setPhone(String userId,String phone,String verification);
    //修改登录密码
    ResultMessage setPassword(String userId,String password);
    //设置支付密码
    ResultMessage setPaymentPassword(String userId,String pay_code);



}
