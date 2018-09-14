package com.ipnet.blservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.EvaluationVO;

public interface EvaluationBLService {

    ResultMessage submitReport(String patentID,String url, int evaluation,double money);

    ResultMessage applyEvaluation(String patentID, String url);

    String getEvaluationApplicationURL(String patentID);

    EvaluationVO getEvaluation(String patentID);

    boolean ifValue(String patentID);

    //ResultMessage ifCompensate(String loanID, String insuranceID, boolean ifPass);

}
