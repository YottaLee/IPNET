package com.ipnet.controller.loancontroller;

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
     * @param patentID 专利号
     * @return 0-专利持有人 1-金融机构 2-保险公司 3-评估机构 4-政府
     */
    @RequestMapping("/getContract")
    @ResponseBody
    public ArrayList<String> getContract(String patentID){
        return null;
    }

    /**
     * 存取该合同的用户是否同意并签署最终合同
     * @param patentID 专利号
     * @param user 用户
     * @param ifPass 是否同意
     * @return
     */
    @RequestMapping("/ifContract")
    @ResponseBody
    public String ifContact(String patentID,String user,Boolean ifPass){
        return null;
    }
}
