package com.ipnet.controller.loancontroller;


import com.ipnet.blservice.loanblservice.LoanApplicantBLService;
import com.ipnet.enums.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 贷款-专利持有人部分
 */
@Controller
@RequestMapping("applicant/")
public class LoanApplicantController {

    @Autowired
    private LoanApplicantBLService loanBLService;

    /**
     * 将该专利的质押贷款保证保险申请提供给保险公司
     *
     * @param loanID    贷款号
     * @param url       专利质押保险投保单url
     * @param insurance 选择的保险公司
     * @return ResultMessage
     */
    @RequestMapping("/chooseInsurance")
    @ResponseBody
    public ResultMessage chooseInsurance(String loanID, String url, String insurance) {
        return loanBLService.chooseInsurance(loanID, url, insurance);
    }

    /**
     * 获取保险申请文件的url
     *
     * @param loanID 贷款号
     * @return url
     */
    @RequestMapping("/getChooseInsuranceURL")
    @ResponseBody
    public String getChooseInsuranceURL(String loanID) {
        return loanBLService.getPolicy(loanID);
    }

    /**
     * 存取该专利贷款意向结果
     *
     * @param patentID 专利号
     * @param userID   用户ID
     * @param url      意向申请的文件路径
     * @param money    意向金额
     * @param time     意向期限
     * @param way      贷款用途
     * @param bank     金融机构
     * @return String
     */
    @RequestMapping("/chooseBank")
    @ResponseBody
    public String chooseBank(String patentID, String userID, String url, double money, String time, String way, String bank) {
        return loanBLService.chooseBank(patentID, userID, url, money, time, way, bank);
    }

    /**
     * 获取贷款意向文件的url
     *
     * @param loanID 贷款号
     * @return url
     */
    @RequestMapping("/getChooseBankURL")
    @ResponseBody
    public String getChooseBankURL(String loanID) {
        return loanBLService.getApplicationToBank(loanID);
    }

    /**
     * 存取该专利已经有贷款申请，可借此机会生成loanID
     *
     * @param userID   用户ID
     * @param patentID 专利号
     * @return 返回loanID
     */
    @RequestMapping("/applyLoan")
    @ResponseBody
    public String saveLoanApply(String userID, String patentID) {
        return loanBLService.saveLoanApply(userID, patentID);
    }

    /**
     * 判断该专利是否已经拥有评估结果
     *
     * @param patentID 专利号
     * @return boolean
     */
    @RequestMapping("/ifValue")
    @ResponseBody
    public boolean ifValue(String patentID) {
        return loanBLService.ifValue(patentID);
    }

    /**
     * 判断该专利是否已经填写贷款意向信息
     *
     * @param loanID 贷款号
     * @return boolean
     */
    @RequestMapping("/ifBankChosen")
    @ResponseBody
    public boolean ifBankChosen(String loanID) {
        return loanBLService.ifBankChosen(loanID);
    }

    /**
     * 改变贷款状态，将贷款状态从待评估中转变成申请贷款中
     *
     * @param loanID 贷款ID
     * @return
     */
    @RequestMapping("/changeEvaluationState")
    @ResponseBody
    public ResultMessage changeEvaluationState(String loanID) {
        return loanBLService.changeEvaluationState(loanID);
    }

    /**
     * 成功购买了保险
     * @param loanID 贷款号
     * @return
     */
    @RequestMapping("/successPayForInsurance")
    @ResponseBody
    public String successPayForInsurance(String loanID) {
        return loanBLService.successPayForInsurance(loanID);
    }

    @RequestMapping("/loanSuccess")
    @ResponseBody
    public String loanSuccess(String loanID,String transactionId) {
        return loanBLService.loanSuccess(loanID,transactionId);
    }

}

