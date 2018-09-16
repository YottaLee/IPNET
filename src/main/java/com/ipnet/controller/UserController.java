package com.ipnet.controller;

import com.ipnet.blservice.UserBLService;
import com.ipnet.dao.PersonalUserDao;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.vo.uservo.CompanyVerify;
import com.ipnet.vo.uservo.EmailRegister;
import com.ipnet.vo.uservo.LoginReq;
import com.ipnet.vo.uservo.PersonVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBLService userBLService;

    @RequestMapping("/test")
    public @ResponseBody
    List<Integer> check(){
        return userBLService.getMemberSum();
//        return userBLService.getEvaluationName();
    }

    @RequestMapping("/test2")
    public @ResponseBody
    List<Integer> test(){
        return userBLService.getUserSum();
//        return userBLService.getEvaluationName();
    }

    //个人用户使用手机号码注册
    /**
    描述：获取短信验证码
    返回值：key--value(注册验证时再传到后端)
            time--限制时间
            hash--哈希值
            若用户已存在，返回null
     */
    @RequestMapping("/getCode")
    public @ResponseBody
    Map<String,String> getCode(String telephone){
        return userBLService.getMessageCode(telephone);
    }

    /**
    描述：注册，核对验证码
    参数：key--value
          phoneNum--号码
          pass--密码
          time--限制时间
          hash--哈希值
          code--用户填写的验证码
    返回值：Success--注册成功
            Timeout--超时
            CodeError--验证码错误
    */

    @RequestMapping("/verify")
    public @ResponseBody
    ResultMessage personalPhoneRegister(@RequestBody Map<String,String> request){
        return userBLService.registerByPhone(request);
    }

    /**
    描述：个人邮箱注册
    参数：EmailRegister(email+password+role(角色可忽略))
    返回值：Success--发送链接成功
            Exist
     */
    @RequestMapping("/emailRegister")
    public @ResponseBody
    ResultMessage EmailRegister(@RequestBody EmailRegister register){
        if(register.getRole()== Role.PersonalUser){
            return userBLService.personalEmailRegister(register);
        }else{
            return userBLService.companyRegister(register);
        }
    }

    //激活邮箱
    @RequestMapping("/checkEmail")
    public @ResponseBody
    ResultMessage checkEmail(String email,String code){
        return userBLService.checkEmail(email,code);
    }

    /**
    描述：通过手机号码登录（仅限个人用户）
    参数：LoginReq(username+password)
    返回值：PersonLogin--登录成功
            NoUser--用户不存在
            PassError--密码错误
     */
    @RequestMapping("/phoneLogin")
    public @ResponseBody
    ResultMessage loginByPhone(@RequestBody LoginReq loginReq){
        System.out.println(loginReq.toString());
        return userBLService.loginPhone(loginReq.getUsername(),loginReq.getPassword());
    }

    /**
    描述：通过邮箱登录（企业或个人）
    参数：LoginReq(username+password)
    返回值：PersonLogin--个人用户登录成功
            CompanyLogin--企业用户登录成功
            EvaluatorLogin--评估机构登录成功
            FinancialLogin--金融机构登录
            InsuranceLogin--保险机构
            NoUser--用户不存在
            PassError--密码错误
     */
    @RequestMapping("/emailLogin")
    public @ResponseBody
    ResultMessage loginByEmail(@RequestBody LoginReq loginReq){
        return userBLService.loginEmail(loginReq.getUsername(),loginReq.getPassword());
    }

    //个人身份认证
    @RequestMapping("personVerify")
    public @ResponseBody
    boolean personVerify(@RequestBody PersonVerify personVerify){
        return userBLService.personVerify(personVerify);
    }
    //企业信息认证
    @RequestMapping("companyVerify")
    public @ResponseBody
    boolean companyVerify(@RequestBody CompanyVerify companyVerify){
        return userBLService.companyVerify(companyVerify);
    }
    //查看个人身份信息
    //查看企业身份信息

    //用户是否通过实名认证
    @RequestMapping("isVerified")
    public @ResponseBody
    boolean isVerified(String userID){
        return userBLService.isVerified(userID);
    }
    //用户是否已经绑定手机号
    @RequestMapping("hasPhone")
    public @ResponseBody
    boolean hasTelephone(String userID){
        return userBLService.hasTelephone(userID);
    }

    /**
     * 返回用户的身份
     * @param userID 用户ID
     * @return 用户角色
     */
    @RequestMapping("/getUserRole")
    @ResponseBody
    public Role getUserRole(String userID){
        return userBLService.getUserRole(userID);
    }
}
