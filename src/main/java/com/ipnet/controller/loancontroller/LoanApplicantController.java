package com.ipnet.controller.loancontroller;

import com.ipnet.enums.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * 贷款-专利持有人部分
 */
@Controller
@RequestMapping("applicant/")
public class LoanApplicantController {

    /**
     * 将该专利的质押贷款保证保险申请提供给保险公司
     *
     * @param patentID 专利号
     * @param insurance 选择的保险公司
     * @return
     */
    @RequestMapping("/chooseInsurance")
    @ResponseBody
    public ResultMessage chooseInsurance(String patentID, String insurance){
        return null;
    }

    /**
     * 存取该专利贷款意向结果
     *
     * @param patentID 专利号
     * @param money 意向金额
     * @param time 意向期限
     * @param bank 金融机构
     * @return
     */
    @RequestMapping("/chooseBank")
    @ResponseBody
    public ResultMessage chooseBank(String patentID,int money,String time,String bank){
        return null;
    }


    /**
     * 将该专利的贷款意向信息反馈给各银行
     *
     * @param patentID
     * @return
     */
//    @RequestMapping("/tellBank")
//    @ResponseBody
//    public String StringtellBank(String patentID){
//        return null;
//    }

    /**
     * 判断该专利是否已经拥有评估结果
     *
     * @param patentID 专利号
     * @return
     */
    @RequestMapping("/ifValue")
    @ResponseBody
    public boolean ifValue(String patentID){
        return false;
    }

    /**
     * 判断该专利是否已经填写贷款意向信息
     *
     * @param patentID
     * @return
     */
    @RequestMapping("/ifBankChosen")
    @ResponseBody
    public boolean ifBankChosen(String patentID){
        return false;
    }



}

