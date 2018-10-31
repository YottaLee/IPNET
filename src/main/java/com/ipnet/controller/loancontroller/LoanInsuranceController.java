package com.ipnet.controller.loancontroller;

import com.ipnet.blservice.loanblservice.LoanInsuranceBLService;
import com.ipnet.enums.IfPass;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.InsuranceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


/**
 * 贷款-保险部分
 */
@Controller
@RequestMapping("insurance/")
public class LoanInsuranceController {


    @Autowired
    private LoanInsuranceBLService loanInsuranceBLService;

    /**
     * @Author: Jane
     * @Description: insurancevo的id由后台自动生成 格式为yyyyMMdd-HHmmss-person
     * @Date: 2018/9/29 10:39
     */
//    @RequestMapping("/createNewInsurance")
//    public  @ResponseBody ResultMessage createInsurance(@RequestBody InsuranceVO insuranceVO){
//        return loanInsuranceBLService.createInsurance(insuranceVO);
//    }

    /**
     * 获取保险信息
     * @param loanid 贷款号
     * @return 保险信息
     */
    @RequestMapping("/getInsurance")
    public @ResponseBody InsuranceVO getInsurance(@RequestParam String loanid) throws IDNotExistsException {
        return loanInsuranceBLService.getInsurance(loanid);
    }

    /**
     * 存是否愿意投保
     *
     * @param loanId 贷款号
     * @param ifPass   保险公司是否愿意投保
     * @return ResultMessage
     */
    @RequestMapping("/ifInsurance")
    public @ResponseBody ResultMessage ifInsurance(@RequestParam String loanId,@RequestParam boolean ifPass){
        return loanInsuranceBLService.ifInsurance(loanId,ifPass);
    }

    //id 保险机构的id
    //保险机构查看保险列表
    @RequestMapping("/showListForInsurance")
    public @ResponseBody ArrayList<InsuranceVO> showListForInsurance(@RequestParam String id) throws IDNotExistsException {
        return loanInsuranceBLService.showListForInsurance(id);
    }


    @RequestMapping("/hasInsurance")
    public @ResponseBody ResultMessage hasInsurance(@RequestParam String loanId) throws IDNotExistsException {
        return loanInsuranceBLService.hasInsurance(loanId);
    }

    @RequestMapping("/overdue")
    public @ResponseBody ResultMessage overDue(@RequestParam String loanId) throws IDNotExistsException {
        return loanInsuranceBLService.overDue(loanId);
    }





}
