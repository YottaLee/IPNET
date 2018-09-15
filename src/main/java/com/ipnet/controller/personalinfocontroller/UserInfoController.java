package com.ipnet.controller.personalinfocontroller;

import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.personalservice.ElectronicWalletBLService;
import com.ipnet.blservice.personalservice.SecurityBLService;
import com.ipnet.blservice.personalservice.UserInfoBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.UserType;
import com.ipnet.vo.CreditCard;
import com.ipnet.vo.uservo.AccountInfoVo;
import com.ipnet.vo.uservo.CompanyUserSaveVo;
import com.ipnet.vo.uservo.PersonalUserSaveVo;
import com.ipnet.vo.uservo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * by nan
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoBLService userBLService;
    @Autowired
    private SecurityBLService securityBLService;
    @Autowired
    private ElectronicWalletBLService electronicWalletBLService;

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
    UserInfoVo getUserInfo(String userid,UserType userType){
        return userBLService.getUserInfo(userid,userType);
    }

    //账户信息查看
    @RequestMapping("/getAccount")
    public @ResponseBody
    AccountInfoVo getAccountInfo (String userId){
        return userBLService.getAccountInfo(userId);
    }

    //查看是否完成验证
    @RequestMapping("/getValid")
    public @ResponseBody
    ResultMessage isUserValidate (String userId){
        return userBLService.isUserValidate(userId);
    }

    //用户名设置
    @RequestMapping("/setUsername")
    public @ResponseBody
    ResultMessage setUsername(String userId,String username){
        return securityBLService.setUsername(userId,username);
    }

    //查看是否已绑定邮箱
    @RequestMapping("/isEmailValidate")
    public @ResponseBody
    ResultMessage isEmailValidate(String userId){
        return securityBLService.isEmailValidate(userId);
    }
    //绑定邮箱
    @RequestMapping("/setEmail")
    public @ResponseBody
    ResultMessage setEmail(String useId,String email){
        return securityBLService.setEmail(useId,email);
    }
    //查看是否已绑定手机号
    @RequestMapping("/isPhone")
    public @ResponseBody
    ResultMessage isPhoneValidate(String userId){
        return securityBLService.isPhoneValidate(userId);
    }
    //获取验证码
    @RequestMapping("/getVerification")
    public @ResponseBody
    ResultMessage getverification(String phone){
        return securityBLService.getverification(phone);
    }
    //绑定手机号
    @RequestMapping("/setPhone")
    public @ResponseBody
    ResultMessage setPhone(String userId,String phone,String verification){
        return securityBLService.setPhone(userId,phone,verification);
    }
    //修改登录密码
    @RequestMapping("/setPassword")
    public @ResponseBody
    ResultMessage setPassword(String userId,String password){
        return securityBLService.setPassword(userId,password);
    }
    //设置支付密码
    @RequestMapping("/setPayMentPassword")
    public @ResponseBody
    ResultMessage setPaymentPassword(String userId,String pay_code){
        return securityBLService.setPaymentPassword(userId,pay_code);
    }

    //查看账户余额
    @RequestMapping("/getAccountBalance")
    public @ResponseBody
    Double getAccountBalance(String userId){
        return electronicWalletBLService.getAccountBalance(userId);
    }
    //显示所有银行卡卡号
    @RequestMapping("/getAllAccountId")
    public @ResponseBody
    List<String> getAllAccountId(String userId){
        return electronicWalletBLService.getAllAccountId(userId);
    }
    //余额充值
    @RequestMapping("/chargeBalance")
    public @ResponseBody
    ResultMessage chargeBalance(String userId,double rmb_num,String card){
        return electronicWalletBLService.chargeBalance(userId,rmb_num,card);
    }
    //余额提现
    @RequestMapping("/withDrawBalance")
    public @ResponseBody
    ResultMessage withDrawBalance(String userId,double rmb_num,String card){
        return electronicWalletBLService.withDrawBalance(userId,rmb_num,card);
    }
    //查看银行卡信息
    @RequestMapping("/getCreditCardInfo")
    public @ResponseBody
    List<CreditCard> getCreditCardInfo(String userId){
        return electronicWalletBLService.getCreditCardInfo(userId);
    }
    //绑定银行卡
    @RequestMapping("/setCreditCard")
    public @ResponseBody
    ResultMessage setCreditCard(String userId,String card,String card_code,String bank){
        return electronicWalletBLService.setCreditCard(userId,card,card_code,bank);
    }
    //银行卡解绑
    @RequestMapping("/cancelCreditCard")
    public @ResponseBody
    ResultMessage cancelCreditCard(String uerId,String card){
        return electronicWalletBLService.cancelCreditCard(uerId,card);
    }
    //查看积分
    @RequestMapping("/getPoint")
    public @ResponseBody
    int getPoint(String userId){
        return electronicWalletBLService.getPoint(userId);
    }
}
