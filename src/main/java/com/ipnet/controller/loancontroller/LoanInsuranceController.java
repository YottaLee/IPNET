package com.ipnet.controller.loancontroller;

import com.ipnet.blservice.LoanBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.ClaimVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 贷款-保险部分
 */
@Controller
@RequestMapping("insurance/")
public class LoanInsuranceController {




    @Autowired
    private LoanBLService loanBLService;


    /**
     * 存是否愿意投保
     *
     * @param loanID 贷款号
     * @param ifPass   保险公司是否愿意投保
     * @return
     */
    @RequestMapping("/ifInsurance")
    @ResponseBody
    public ResultMessage ifInsurance(String loanID, boolean ifPass) {
        return loanBLService.ifInsurance(loanID,ifPass);
    }

    /**
     * 获取金融机构的理赔信息列表
     *
     * @param person 贷款号
     * @return 保险申请信息
     */
    @RequestMapping("/getInsurance")
    @ResponseBody
    public ClaimVO getInsurance(String person) {
        return null;
    }


}
