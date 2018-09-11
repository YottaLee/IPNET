package com.ipnet.controller.evaluationcontroller;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.EvaluationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("evaluation/")
public class EvaluationController {

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
        return null;
    }

    /**
     * 获取评估报告
     * @param patentID 专利号
     * @return 评估报告
     */
    @RequestMapping("/getEvaluation")
    @ResponseBody
    public EvaluationVO getEvaluation(String patentID) {
        return null;
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
        return null;
    }

    /**
     * 获取申请评估的文件url
     * @param patentID 专利号
     * @return 专利持有人申请评估文件的url
     */
    @RequestMapping("/getEvaluationApplication")
    @ResponseBody
    public String getEvaluationApplication(String patentID) {
        return null;
    }


}
