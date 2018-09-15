package com.ipnet.controller.loancontroller;

import com.ipnet.blservice.loanblservice.LoanClaimBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.ClaimVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/claim")
public class LoanClaimController {

    @Autowired
    private LoanClaimBLService loanClaimBLService;
    //银行、保险机构、评估机构查看（待）赔付列表
    @RequestMapping("/showList")
    public @ResponseBody
    ArrayList<ClaimVO> showList(@RequestParam String id){
        return loanClaimBLService.showList(id);
    }

    //保险机构赔钱
    @RequestMapping("/insurancePay")
    public @ResponseBody
    ResultMessage insurancePay(String id,double money){
        return loanClaimBLService.insurancePay(id,money);
    }

    //评估机构赔钱
    @RequestMapping("/evaluationPay")
    public @ResponseBody ResultMessage evaluationPay(String id,double money){
        return loanClaimBLService.evaluationPay(id,money);
    }
}
