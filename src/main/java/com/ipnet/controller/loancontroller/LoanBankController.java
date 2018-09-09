package com.ipnet.controller.loancontroller;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 贷款-银行部分
 */
@Controller
@RequestMapping("bank/")
public class LoanBankController {



    /**
     * 获取贷款信息
     * @param loanID 贷款号
     * @return 贷款信息
     */
    @RequestMapping("/getInfo")
    @ResponseBody
    public LoanVO getInfo(String loanID) {
        return null;
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
        return null;
    }

    /**
     * 银行提交通过意见
     * @param loanID 贷款号
     * @param bank 金融机构名称
     * @param ifPass 是否同意放贷
     * @param ifInsurance 是否同意让专利持有人购买专利质押贷款保证保险
     * @param money 放贷金额
     * @param time 放贷期限
     * @return
     */
    @RequestMapping("/submitApplication")
    @ResponseBody
    public ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time) {
        return null;
    }

    /**
     * 获取选择放贷银行的贷款信息
     * @param patentID
     * @return
     */
//    @RequestMapping("/getBankList")
//    @ResponseBody
//    public ArrayList<Loan> getBankList(String patentID){
//        return null;
//    }

}
