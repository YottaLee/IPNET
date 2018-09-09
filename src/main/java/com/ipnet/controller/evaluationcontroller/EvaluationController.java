package com.ipnet.controller.evaluationcontroller;

import com.ipnet.enums.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("evaluation/")
public class EvaluationController {

    /**
     * 提交专利评估报告
     * @patentID 专利号
     * @evaluation 经济评估价值
     * @return
     */
    @RequestMapping("/submitReport")
    @ResponseBody
    public ResultMessage submitReport(String patentID, int evaluation) {
        return null;
    }
}
