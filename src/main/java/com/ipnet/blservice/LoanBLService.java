package com.ipnet.blservice;

import java.util.ArrayList;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.ClaimVO;
import com.ipnet.vo.financevo.LoanVO;

public interface LoanBLService {


    ResultMessage ifInsurance(String loanID, boolean ifPass);


    ResultMessage Compensate(String loanID, String claimID);

    //LoanBankController
    LoanVO getInfo(String loanID);

    LoanVO getApplication(String loanID);

    ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time);
}
