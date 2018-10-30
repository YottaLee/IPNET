package com.ipnet.blservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.EvaluationVO;
import com.ipnet.vo.financevo.Evaluator;

public interface EvaluationBLService {

    ResultMessage submitReport(String patentID,String url,String rule,String tech, double evaluation,String result, double money);

    ResultMessage applyEvaluation(String patentID, String url);

    String getEvaluationApplicationURL(String patentID);

    EvaluationVO getEvaluation(String patentID);

    boolean ifValue(String patentID);

    Evaluator getEvaluator();

    double getValue(String patentID);

    double smartEvaluation(String patentID);



}
