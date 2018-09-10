package com.ipnet.controller.loancontroller;

import com.ipnet.blservice.LoanBLService;
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
    private LoanBLService loanBLService;

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
        return null;
        // return loanBLService.chooseInsurance(loanID,insurance);
    }

    /**
     * 存取该专利贷款意向结果
     *
     * @param loanID 贷款号
     * @param url 意向申请的文件路径
     * @param money  意向金额
     * @param time   意向期限
     * @param bank   金融机构
     * @return ResultMessage
     */
    @RequestMapping("/chooseBank")
    @ResponseBody
    public ResultMessage chooseBank(String loanID, String url, double money, String time, String bank) {
        return null;
        //return loanBLService.chooseBank(loanID, money, time, bank);
    }


//    /**
//     * 将该专利的贷款意向信息反馈给各银行
//     *
//     * @param patentID
//     * @return
//     */
//    @RequestMapping("/tellBank")
//    @ResponseBody
//    public String StringtellBank(String patentID){
//        return null;
//    }

    /**
     * 存取该专利已经有贷款申请，可借此机会生成loanID
     *
     * @param patentID 专利号
     * @return 返回loanID
     */
    @RequestMapping("/applyLoan")
    @ResponseBody
    public String saveLoanApply(String patentID) {
        return loanBLService.saveLoanApply(patentID);
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
     * @param patentID 专利号???这里应该是loanID？？？？？？？？？？？？
     * @return boolean
     */
    @RequestMapping("/ifBankChosen")
    @ResponseBody
    public boolean ifBankChosen(String patentID) {
        return loanBLService.ifBankChosen(patentID);
    }


}

