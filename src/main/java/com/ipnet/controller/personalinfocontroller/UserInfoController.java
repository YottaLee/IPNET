package com.ipnet.controller.personalinfocontroller;

import com.ipnet.blservice.personalservice.ElectronicWalletBLService;
import com.ipnet.blservice.personalservice.SecurityBLService;
import com.ipnet.blservice.personalservice.UserInfoBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.vo.CreditCard;
import com.ipnet.vo.uservo.AccountInfoVo;
import com.ipnet.vo.uservo.CompanyUserSaveVo;
import com.ipnet.vo.uservo.PersonalUserSaveVo;
import com.ipnet.vo.uservo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    ResultMessage savePersonalUserInfo(@RequestBody PersonalUserSaveVo personalUserSaveVo){
        return userBLService.savePersonalUserInfo(personalUserSaveVo);
    }

    //修改企业用户
    @RequestMapping("/modifyCompany")
    public @ResponseBody
    ResultMessage saveCompanyUserInfo(@RequestBody CompanyUserSaveVo companyUserSaveVo){
        return userBLService.saveCompanyUserInfo(companyUserSaveVo);
    }

    //身份信息查看
    @RequestMapping("/getUser")
    public @ResponseBody
    UserInfoVo getUserInfo(String userid,Role userType){
        return userBLService.getUserInfo(userid,userType);
    }

    //账户信息查看
    @RequestMapping("/getAccount")
    public @ResponseBody
    AccountInfoVo getAccountInfo (String userId,Role userType){
        System.out.println("userID:"+userId);
        System.out.println("userType:"+userType);
        AccountInfoVo accountInfoVo=userBLService.getAccountInfo(userId,userType);
        System.out.println(accountInfoVo);
        System.out.println(accountInfoVo.toString());
        return accountInfoVo;
    }

    //查看是否完成验证
    @RequestMapping("/getValid")
    public @ResponseBody
    ResultMessage isUserValidate (String userId,Role userType){
        return userBLService.isUserValidate(userId,userType);
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
    ResultMessage isEmailValidate(String userId,Role userType){
        return securityBLService.isEmailValidate(userId,userType);
    }
    //绑定邮箱
    @RequestMapping("/setEmail")
    public @ResponseBody
    ResultMessage setEmail(String useId,String email,Role userType){
        return securityBLService.setEmail(useId,email,userType);
    }
    //查看是否已绑定手机号
    @RequestMapping("/isPhone")
    public @ResponseBody
    ResultMessage isPhoneValidate(String userId,Role userType){
        return securityBLService.isPhoneValidate(userId,userType);
    }
    //获取验证码
    @RequestMapping("/getVerification")
    public @ResponseBody
    ResultMessage getverification(String phone,String userId,Role userType){
        return securityBLService.getverification(phone,userId,userType);
    }
    //绑定手机号
    @RequestMapping("/setPhone")
    public @ResponseBody
    ResultMessage setPhone(String userId,String phone,String verification,Role userType){
        return securityBLService.setPhone(userId,phone,verification,userType);
    }
    //修改登录密码
    @RequestMapping("/setPassword")
    public @ResponseBody
    ResultMessage setPassword(String userId,String password,Role userType){
        return securityBLService.setPassword(userId,password,userType);
    }
    //设置支付密码
    @RequestMapping("/setPayMentPassword")
    public @ResponseBody
    ResultMessage setPaymentPassword(String userId,String pay_code,Role userType){
        return securityBLService.setPaymentPassword(userId,pay_code,userType);
    }

    //查看账户余额
    @RequestMapping("/getAccountBalance")
    public @ResponseBody
    Double getAccountBalance(String userId,Role userType){
        return electronicWalletBLService.getAccountBalance(userId,userType);
    }
    //显示所有银行卡卡号
    @RequestMapping("/getAllAccountId")
    public @ResponseBody
    List<String> getAllAccountId(String userId,Role userType){
        return electronicWalletBLService.getAllAccountId(userId,userType);
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
    List<CreditCard> getCreditCardInfo(String userId,Role userType){
        return electronicWalletBLService.getCreditCardInfo(userId,userType);
    }
    //绑定银行卡
    @RequestMapping("/setCreditCard")
    public @ResponseBody
    ResultMessage setCreditCard(String userId,String card,String card_code,String bank,Role userType){
        return electronicWalletBLService.setCreditCard(userId,card,card_code,bank,userType);
    }
    //银行卡解绑
    @RequestMapping("/cancelCreditCard")
    public @ResponseBody
    ResultMessage cancelCreditCard(String uerId,String card,Role userType){
        return electronicWalletBLService.cancelCreditCard(uerId,card,userType);
    }
    //查看积分
    @RequestMapping("/getPoint")
    public @ResponseBody
    int getPoint(String userId,Role userType){
        return electronicWalletBLService.getPoint(userId,userType);
    }

    //更新积分
    @RequestMapping("/updatePoint")
    public @ResponseBody
    ResultMessage updatePoint(String userId,int point,Role userType){
        return electronicWalletBLService.updatePoint(userId,point,userType);
    }
}
