package com.ipnet.controller.loancontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("bank/")
public class LoanBankController {
    @Autowired

    /**
     * 专利持有人未提交贷款，银行向保险公司发出理赔申请
     * @param patentID 专利号
     * @param person 投保单位名称
     * @param address 投保单位地址
     * @param time 出险时间
     * @param reason 出险原因
     * @param bank 开户行
     * @param bankName 户名
     * @param bankID 银行账号
     * @param insuranceID 保单号
     * @param money 申请金额
     * @return
     */
    @RequestMapping("/insuranceApplication")
    @ResponseBody
    public String insuranceApplication(String patentID, String person, String address, String time, String reason,
                                       String bank, String bankName, String bankID, String insuranceID, int money) {
        return null;
    }
}
