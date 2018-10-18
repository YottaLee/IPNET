package com.ipnet.blservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.vo.financevo.Evaluator;
import com.ipnet.vo.uservo.CompanyVerify;
import com.ipnet.vo.uservo.EmailRegister;
import com.ipnet.vo.uservo.PersonVerify;

import java.util.List;
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
    /**
    * 用户登录
    * 方式：手机号登录——仅限个人
    *       邮箱登录——个人或企业
    */

    //手机号登录，仅限个人用户
    ResultMessage loginPhone(String telephone, String password);
    //邮箱登录,企业和个人都可以
    ResultMessage loginEmail(String email, String password);

    Role getUserRole(String userID);

    //获取唯一一家评估机构的名字
    Evaluator getEvaluationName();

    //根据用户ID获得姓名
    String getXingMing(String userID);

    String getCompanyId(String name);

    //用户实名身份认证
    boolean personVerify(PersonVerify personVerify);
    //企业信息实名认证
    boolean companyVerify(CompanyVerify companyVerify);

    //用户是否通过实名认证
    boolean isVerified(String userID);
    //用户是否已经绑定手机号
    boolean hasTelephone(String userID);

    //获取用户的头像的url
    String getImageUrl(String username);


    /**
     * 最近六个月的IP成员数
     * @return 每个月的数量
     */
    List<Integer> getMemberSum();

    /**
     * 最近六个月的IPNet总用户数
     * @return 每个月的数量
     */
    List<Integer> getUserSum();


}
