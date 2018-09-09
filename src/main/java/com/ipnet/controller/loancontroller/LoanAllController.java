package com.ipnet.controller.loancontroller;

import com.ipnet.enums.ResultMessage;
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

    /**
     * 获取该合同的所有方信息
     * @param loanID 贷款号
     * @return 0-专利持有人 1-金融机构 2-保险公司 3-评估机构 4-政府
     */
    @RequestMapping("/getContract")
    @ResponseBody
    public ArrayList<String> getContract(String loanID){
        return null;
    }

    /**
     * 存取该合同的用户是否同意并签署最终合同
     * @param loanID 贷款号
     * @param userid 用户
     * @param ifPass 是否同意
     * @return
     */
    @RequestMapping("/ifContract")
    @ResponseBody
    public ResultMessage ifContact(String loanID, String userid, Boolean ifPass){
        return null;
    }
}
