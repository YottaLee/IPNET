package com.ipnet.blservice.personalservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.CreditCard;

import java.util.List;

/**
 * by nan
 */
public interface ElectronicWalletBLService {
    //查看账户余额
    Double getAccountBalance(String userId);
    //显示所有银行卡卡号
    List<String> getAllAccountId(String userId);
    //余额充值
    ResultMessage chargeBalance(String userId,double rmb_num,String card);
    //余额提现
    ResultMessage withDrawBalance(String userId,double rmb_num,String card);
    //查看银行卡信息
    List<CreditCard> getCreditCardInfo(String userId);
    //绑定银行卡
    ResultMessage setCreditCard(String userId,String card,String card_code);
    //银行卡解绑
    ResultMessage cancelCreditCard(String uerId,String card);
    //查看积分
    int getPoint(String userId);



}
