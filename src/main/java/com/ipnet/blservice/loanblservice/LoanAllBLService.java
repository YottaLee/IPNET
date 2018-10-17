package com.ipnet.blservice.loanblservice;

import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.LoanVO;

import java.util.ArrayList;

public interface LoanAllBLService {

    ArrayList<String> getContract(String loanID);

    ResultMessage ifContract(String loanID, String url, String userid, Boolean ifPass);

    ResultMessage saveGovernmentSign(String loanID, String userid, int gov, String url);

    ArrayList<String> getSigns(String loanID);

    ArrayList<LoanVO> getPatentList(String userID);

    boolean getIfContract(String loanID,String userID);

    String getLatestLoanID(String patentID);

    ResultMessage changeState(String loanID, Patent_loan_state state);

    ResultMessage changeStateByPatentID(String patentID,Patent_loan_state state);

    ResultMessage changeEvaluationByPatentID(String patentID,double evaluation);
}
