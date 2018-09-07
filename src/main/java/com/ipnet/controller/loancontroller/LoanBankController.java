package com.ipnet.controller.loancontroller;

import com.ipnet.vo.financevo.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * 贷款-银行部分
 */
@Controller
@RequestMapping("bank/")
public class LoanBankController {
    @Autowired


    /**
     * 获取贷款信息
     * @patentID 专利号
     * @return 贷款信息
     */
    @RequestMapping("/getInfo")
    @ResponseBody
    public Loan getInfo(String patentID) {
        return null;
    }


    /**
     * 获取贷款意向
     *
     * @param patentID 专利号
     * @return 贷款意向
     */
    @RequestMapping("/getApplication")
    @ResponseBody
    public Loan getApplication(String patentID) {
        return null;
    }

    /**
     * 银行提交通过意见
     * @param patentID 专利号
     * @param bank 金融机构名称
     * @param ifPass 是否同意放贷
     * @param ifInsurance 是否同意让专利持有人购买专利质押贷款保证保险
     * @param money 放贷金额
     * @param time 放贷期限
     * @return
     */
    @RequestMapping("/submitApplication")
    @ResponseBody
    public String submitApplication(String patentID, String bank, boolean ifPass, boolean ifInsurance, int money, String time) {
        return null;
    }

    /**
     * 获取选择放贷银行的贷款信息
     * @param patentID
     * @return
     */
    @RequestMapping("/getBankList")
    @ResponseBody
    public ArrayList<Loan> getBankList(String patentID){
        return null;
    }

}
