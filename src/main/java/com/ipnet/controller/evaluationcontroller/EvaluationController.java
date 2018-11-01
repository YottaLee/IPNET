package com.ipnet.controller.evaluationcontroller;

import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.EvaluationVO;
import com.ipnet.vo.financevo.Evaluator;
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
     * 获取平台唯一的评估机构id
     *
     * @return 评估机构的id
     */
    @RequestMapping("/getEvaluationId")
    @ResponseBody
    public Evaluator getEvaluationId() {
        return evaluationBLService.getEvaluator();
    }

    /**
     * 提交专利评估报告
     *
     * @param patentID   专利号
     * @param url        上传评估报告的文件路径
     * @param evaluation 经济评估价值
     * @param money      评估费用
     * @return ResultMessage
     */
    @RequestMapping("/submitReport")
    @ResponseBody
    public ResultMessage submitReport(String patentID, String url, String rule, String tech, double evaluation, String result, double money) {
        return evaluationBLService.submitReport(patentID, url, rule, tech, evaluation, result, money);
    }

    /**
     * 申请评估
     *
     * @param patentID 专利号
     * @param url      申请评估文件的路径
     * @return ResultMessage
     */
    @RequestMapping("/apply")
    @ResponseBody
    public ResultMessage applyEvaluation(String patentID, String url) {
        return evaluationBLService.applyEvaluation(patentID, url);
    }

    /**
     * 获取申请评估的文件url
     *
     * @param patentID 专利号
     * @return 专利持有人申请评估文件的url
     */
    @RequestMapping("/getEvaluationApplicationURL")
    @ResponseBody
    public String getEvaluationApplicationURL(String patentID) {
        return evaluationBLService.getEvaluationApplicationURL(patentID);
    }

    /**
     * 获取该专利评估的信息
     *
     * @param patentID 专利号
     * @return EvaluationVO
     */
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

    @RequestMapping("/smartEvaluation")
    @ResponseBody
    public double smartEvaluation(String patentID) {
        return evaluationBLService.smartEvaluation(patentID);
    }


}
