package com.ipnet.blservice.loanblservice;

import com.ipnet.enums.ResultMessage;

public interface LoanApplicantBLService {

    ResultMessage chooseInsurance(String loanID, String url, String insurance);

    String getPolicy(String loanID);

    ResultMessage chooseBank(String loanID, String url, double money, String time, String bank);

    String getApplicationToBank(String loanID);

    String saveLoanApply(String userID,String patentID);

    boolean ifValue(String patentID);

    boolean ifBankChosen(String loanID);

    ResultMessage changeEvaluationState(String loanID);

    ResultMessage changeEvaluationStateToEvaluationFinish(String loanID);
}
