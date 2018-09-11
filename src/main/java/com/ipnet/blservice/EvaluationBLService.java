package com.ipnet.blservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.EvaluationVO;

public interface EvaluationBLService {

    /**
     * 提交专利评估报告
     * @param patentID 专利号
     * @param url 上传评估报告的文件路径
     * @param evaluation 经济评估价值
     * @return
     */
    ResultMessage submitReport(String patentID,String url, int evaluation);

    /**
     * 申请评估
     * @param patentID 专利号
     * @param url 申请评估文件的路径
     * @return
     */
    ResultMessage applyEvaluation(String patentID, String url);


    /**
     * 判断该专利是否已经拥有评估结果
     *
     * @param patentID 专利号
     * @return boolean
     */
    boolean ifValue(String patentID);


    EvaluationVO getEvaluation(String patentID);


}
