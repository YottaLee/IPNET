package com.ipnet.blservice.personalservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.enums.UserType;

/**
 * by nan
 */
public interface SecurityBLService {

    //用户名设置
    ResultMessage setUsername(String userId,String username);
    //查看是否已绑定邮箱
    ResultMessage isEmailValidate(String userId,Role userType);
    //绑定邮箱
    ResultMessage setEmail(String useId,String email,Role userType);
    //查看是否已绑定手机号
    ResultMessage isPhoneValidate(String userId,Role userType);
    //获取验证码
    ResultMessage getverification(String phone,String userId,Role userType);
    //绑定手机号
    ResultMessage setPhone(String userId,String phone,String verification,Role userType);
    //修改登录密码
    ResultMessage setPassword(String userId,String password,Role userType);
    //设置支付密码
    ResultMessage setPaymentPassword(String userId,String pay_code,Role userType);



}
