package com.ipnet.controller;

import com.ipnet.blservice.UserBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.uservo.LoginReq;
import com.ipnet.vo.uservo.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBLService userBLService;

    @RequestMapping("/registerTest")
    public @ResponseBody
    void test(String username,String password){
        userBLService.personalRegister(username,password);
    }

    //发送短信验证码
    @RequestMapping("sendMessageCode")
    public @ResponseBody
    String getCode(String telephone){
        return userBLService.getMessageCode(telephone);
    }

    //发送验证邮件
    @RequestMapping("sendEmail")
    public @ResponseBody
    void sendEmail(String toEmail){
        userBLService.sendHtmlMail(toEmail);
        //return userBLService.sendEmail(toEmail);
    }

    //用户注册
    @RequestMapping("register")
    public @ResponseBody
    ResultMessage companyRegister(NewUser newUser){
        switch (newUser.getRole()){
            case CompanyUser:
                return userBLService.companyRegister(newUser.getUsername(),newUser.getPassword());
            case PersonalUser:
                return userBLService.personalRegister(newUser.getUsername(),newUser.getPassword());
            case Evaluator:
            case Financial:
            case Insurance:
            default:
                return null;
        }
    }

    //用户登录
    @RequestMapping("login")
    public @ResponseBody
    ResultMessage login(LoginReq loginReq){
        return userBLService.login(loginReq.getUsername(),loginReq.getPassword());
    }

    //用户注册
    @RequestMapping("logout")
    public @ResponseBody
    ResultMessage logout(String username){
        return userBLService.logout(username);
    }
}
