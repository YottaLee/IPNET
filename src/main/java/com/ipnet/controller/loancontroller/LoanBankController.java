package com.ipnet.controller.loancontroller;

import com.ipnet.blservice.loanblservice.LoanBankBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 贷款-银行部分
 */
@Controller
@RequestMapping("bank/")
public class LoanBankController {

    @Autowired
    private LoanBankBLService loanBankBLService;

    /**
     * 获取贷款信息
     *
     * @param loanID 贷款号
     * @return 贷款信息
     */
    @RequestMapping("/getInfo")
    @ResponseBody
    public LoanVO getInfo(String loanID) {
        return loanBankBLService.getInfo(loanID);
    }


    /**
     * 获取贷款意向
     *
     * @param loanID 贷款号
     * @return 贷款意向
     */
    @RequestMapping("/getApplication")
    @ResponseBody
    public LoanVO getApplication(String loanID) {
        return loanBankBLService.getApplication(loanID);
    }

    /**
     * 银行提交通过意见
     *
     * @param loanID 贷款号
     * @param ifPass 是否通过预评估
     * @return
     */
    @RequestMapping("/submitApplication")
    @ResponseBody
    public ResultMessage submitApplication(String loanID, boolean ifPass) {
        return loanBankBLService.submitApplication(loanID, ifPass);
    }

    /**
     * 提交中期审核
     *
     * @param loanID      贷款号
     * @param ifPass      是否通过
     * @param ifInsurance 是否让专利持有人购买专利质押贷款保证保险
     * @return
     */
    @RequestMapping("/submitMidApplication")
    @ResponseBody
    public ResultMessage submitMidApplication(String loanID, boolean ifPass, boolean ifInsurance) {
        return loanBankBLService.submitMidApplication(loanID, ifPass, ifInsurance);
    }

    @RequestMapping("/submitDecision")
    @ResponseBody
    public ResultMessage submitDecision(String loanID, boolean ifPass) {
        return loanBankBLService.submitDecision(loanID, ifPass);
    }



    /**
     * 银行提交协议
     * @param loanID 贷款号
     * @param loanMoney 贷款金额
     * @param returnMoreMoney 还款金额
     * @param duration 时长
     * @param compensation 赔偿金额
     * @return
     */
    @RequestMapping("/hasContract")
    @ResponseBody
    public ResultMessage hasContract(String loanID, double loanMoney, double returnMoreMoney, String duration, double compensation) {
        return loanBankBLService.hasContract(loanID, loanMoney, returnMoreMoney, duration, compensation);
    }


}
