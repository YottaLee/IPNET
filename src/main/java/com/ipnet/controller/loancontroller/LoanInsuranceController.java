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
//    /**
//     * 专利持有人未提交贷款，银行向保险公司发出理赔申请
//     *
//     * @param url 理赔申请文件路径
//     * @param loanID 贷款号
//     * @param person 投保单位名称
//     * @param address 投保单位地址
//     * @param time 出险时间
//     * @param reason 出险原因
//     * @param bank 开户行
//     * @param bankName 户名
//     * @param bankID 银行账号
//     * @return
//     */
//    @RequestMapping("/insuranceApplication")
//    @ResponseBody
//    public String insuranceApplication(String loanID, String url,String person, String address, String time, String reason,
//                                       String bank, String bankName, String bankID) {
//        SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd-HHmmss");
//        String insuranceID=df.format(new Date())+person;
//        loanBLService.insuranceApplication(loanID,url,person,address,time,reason,bank,bankName,bankID,insuranceID);
//        return insuranceID;
//    }


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
     * @param loanID 贷款号
     * @return 保险申请信息
     */
    @RequestMapping("/getInsurance")
    @ResponseBody
    public ClaimVO getInsurance(String loanID) {
        return loanBLService.getInsurance(loanID);
    }

//    /**
//     * 存取保险公司是否同意理赔
//     *
//     * @param loanID 贷款号
//     * @param claimID 理赔单
//     * @param ifPass 是否同意
//     * @return
//     */
//    @RequestMapping("/ifCompensate")
//    @ResponseBody
//    public ResultMessage ifCompensate(String loanID, String claimID, boolean ifPass) {
//        return loanBLService.ifCompensate(loanID,claimID,ifPass);
//    }

}
