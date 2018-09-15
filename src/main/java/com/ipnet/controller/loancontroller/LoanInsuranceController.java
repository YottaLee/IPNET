package com.ipnet.controller.loancontroller;

import com.ipnet.blservice.loanblservice.LoanInsuranceBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.InsuranceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * 获取保险信息
     * @param id 贷款号
     * @return 保险信息
     */
    @RequestMapping("/getInsurance")
    public @ResponseBody InsuranceVO getInsurance(@RequestParam String id) throws IDNotExistsException {
        return loanInsuranceBLService.getInsurance(id);
    }

    /**
     * 存是否愿意投保
     *
     * @param id 贷款号
     * @param ifPass   保险公司是否愿意投保
     * @return ResultMessage
     */
    @RequestMapping("/ifInsurance")
    public @ResponseBody ResultMessage ifInsurance(@RequestParam String id,@RequestParam boolean ifPass){
        return loanInsuranceBLService.ifInsurance(id,ifPass);
    }

    //保险机构查看保险列表
    @RequestMapping("/showListForInsurance")
    public @ResponseBody ArrayList<InsuranceVO> showListForInsurance(@RequestParam String id) throws IDNotExistsException {
        return loanInsuranceBLService.showListForInsurance(id);
    }





}
