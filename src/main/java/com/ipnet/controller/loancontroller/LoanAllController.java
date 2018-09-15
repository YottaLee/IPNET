package com.ipnet.controller.loancontroller;

import com.ipnet.blservice.loanblservice.LoanAllBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * 所有人的合同
 */
@Controller
@RequestMapping("all/")
public class LoanAllController {

    @Autowired
    private LoanAllBLService loanBLService;

    /**
     * 获取该合同的所有方信息
     *
     * @param loanID 贷款号
     * @return 0-专利持有人 1-金融机构 2-保险公司 3-评估机构 4-政府
     */
    @RequestMapping("/getContract")
    @ResponseBody
    public ArrayList<String> getContract(String loanID) {
        return loanBLService.getContract(loanID);
    }

    /**
     * 存取该合同的用户是否同意并签署最终合同
     *
     * @param loanID 贷款号
     * @param url    上传签字的url
     * @param userid 用户
     * @param ifPass 是否同意
     * @return ResultMessage
     */
    @RequestMapping("/ifContract")
    @ResponseBody
    public ResultMessage ifContract(String loanID, String url, String userid, Boolean ifPass) {
        return loanBLService.ifContract(loanID,url,userid,ifPass);
    }

    /**
     * 该用户是否已在该合同最终确认（此步接上个controller）
     * @param loanID 贷款号
     * @param userid 用户ID
     * @return 是否确认过合同
     */
    @RequestMapping("/getIfContract")
    @ResponseBody
    public Boolean getIfContract(String loanID, String userid) {
        return loanBLService.getIfContract(loanID,userid);
    }

    /**
     * 存取合同url
     * @param loanID 贷款ID
     * @param userid 用户ID
     * @param gov 如果用户为专利持有人，此参数有效，0为知识产权局，1为财政局
     * @param url url
     * @return ResultMessage
     */
    @RequestMapping("/saveContractURL")
    @ResponseBody
    public ResultMessage saveContractURL(String loanID, String userid, int gov, String url) {
        return loanBLService.saveGovernmentSign(loanID,userid,gov,url);
    }

    /**
     * 获取合同照片的url
     *
     *
     * @param loanID 贷款号
     * @return 0-知识产权局 1-财政局 2-金融机构 3-保险公司 4-评估机构, 如果没有该项返回""
     */
    @RequestMapping("/getContractURL")
    @ResponseBody
    public ArrayList<String> getContractURL(String loanID) {
        return loanBLService.getSigns(loanID);
    }

    /**
     * 返回金融机构、保险公司、评估机构有关的所有专利信息
     * @param userId 用户id
     * @return 专利列表
     */
    @RequestMapping("/getPatentList")
    @ResponseBody
    public ArrayList<LoanVO> getPatentList(String userId) {

        return loanBLService.getPatentList(userId);
    }

    /**
     * 根据专利号得到最近一次质押号
     * @param patentID 专利号
     * @return 最近一次质押号，如果没有质押记录，返回null
     */
    @RequestMapping("/getLatestLoan")
    @ResponseBody
    public String getLatestLoanID(String patentID){
        return loanBLService.getLatestLoanID(patentID);
    }

}
