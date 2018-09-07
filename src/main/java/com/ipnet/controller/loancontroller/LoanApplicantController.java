package com.ipnet.controller.loancontroller;

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
    @Autowired

    /**
     * 将该专利的质押贷款保证保险申请提供给保险公司
     *
     * @param patentID 专利号
     * @param insuranceList 选择的保险公司列表
     * @return
     */
    @RequestMapping("/chooseInsurance")
    @ResponseBody
    public String chooseInsurance(String patentID,ArrayList<String> insuranceList){
        return null;
    }

    /**
     * 存取该专利贷款意向结果
     *
     * @param patentID 专利号
     * @param money 意向金额
     * @param time 意向期限
     * @param bankList 金融机构列表
     * @return
     */
    @RequestMapping("/chooseBank")
    @ResponseBody
    public String chooseBank(String patentID,int money,String time,ArrayList<String> bankList){
        return null;
    }

    /**
     * 存取最终确认选择的银行，同时通知其他银行
     *
     * @param patentID 专利号
     * @param bank 银行
     * @return
     */
    @RequestMapping("/sureBank")
    @ResponseBody
    public String sureBank(String patentID,String bank){
        return null;
    }


    /**
     * 将该专利的贷款意向信息反馈给各银行
     *
     * @param patentID
     * @return
     */
    @RequestMapping("/tellBank")
    @ResponseBody
    public String tellBank(String patentID){
        return null;
    }

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

