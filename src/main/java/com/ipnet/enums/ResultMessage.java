package com.ipnet.enums;

public enum ResultMessage {
    RegisterSuccess,
    Exist,//用户已存在
    Fail,
    Success,
    NoUser,//用户不存在
    PassError,//密码错误
    Timeout,//超时
    NotActive,//用户邮箱未激活
    CodeError,//验证码错误
    PersonLogin,//个人用户登录
    CompanyLogin,//企业用户登录
    EvaluatorLogin,//评估机构
    FinancialLogin,//金融机构
    InsuranceLogin//保险机构
}
