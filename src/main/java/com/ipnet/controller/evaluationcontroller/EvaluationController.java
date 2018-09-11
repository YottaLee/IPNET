package com.ipnet.controller.evaluationcontroller;

import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.EvaluationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("evaluation/")
public class EvaluationController {

    @Autowired
    private EvaluationBLService evaluationBLService;

    /**
     * 提交专利评估报告
     * @param patentID 专利号
     * @param url 上传评估报告的文件路径
     * @param evaluation 经济评估价值
     * @return
     */
    @RequestMapping("/submitReport")
    @ResponseBody
    public ResultMessage submitReport(String patentID,String url, int evaluation) {
        return evaluationBLService.submitReport(patentID,url,evaluation);
    }

    /**
     * 申请评估
     * @param patentID 专利号
     * @param url 申请评估文件的路径
     * @return
     */
    @RequestMapping("/apply")
    @ResponseBody
    public ResultMessage applyEvaluation(String patentID, String url) {
        return evaluationBLService.applyEvaluation(patentID,url);
    }

    /**
     * 获取申请评估的文件url
     * @param patentID 专利号
     * @return 专利持有人申请评估文件的url
     */
    @RequestMapping("/getEvaluationApplicationURL")
    @ResponseBody
    public String getEvaluationApplicationURL(String patentID) {
        return null;
    }

    @RequestMapping("/getEvaluation")
    @ResponseBody
    public EvaluationVO getEvaluation(String patentID) {
        return evaluationBLService.getEvaluation(patentID);
    }

    /**
     * 判断该专利是否已经拥有评估结果
     *
     * @param patentID 专利号
     * @return boolean
     */
    @RequestMapping("/ifValue")
    @ResponseBody
    public boolean ifValue(String patentID) {
        return evaluationBLService.ifValue(patentID);
    }

    /**
     * 存取评估机构是否同意理赔
     *
     * @param loanID 贷款号
     * @param insuranceID 保单号
     * @param ifPass 是否同意
     * @return
     */
    @RequestMapping("/ifCompensate")
    @ResponseBody
    public ResultMessage ifCompensate(String loanID, String insuranceID, boolean ifPass) {
        return null;
    }

}
