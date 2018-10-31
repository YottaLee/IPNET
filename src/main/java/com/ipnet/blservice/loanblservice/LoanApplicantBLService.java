package com.ipnet.blservice.loanblservice;

import com.ipnet.enums.ResultMessage;

public interface LoanApplicantBLService {

    ResultMessage chooseInsurance(String loanID, String url, String insurance);

    String getPolicy(String loanID);

    String chooseBank(String patentID,String userID, String url, double money, String time,String way, String bank);

    String getApplicationToBank(String loanID);

    String saveLoanApply(String userID,String patentID);

    boolean ifValue(String patentID);

    boolean ifBankChosen(String loanID);

    ResultMessage changeEvaluationState(String loanID);

    String successPayForInsurance(String loanID);

    String loanSuccess(String loanID,String transactionId);
}
