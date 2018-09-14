package com.ipnet.blservice;

import java.util.ArrayList;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.ClaimVO;
import com.ipnet.vo.financevo.LoanVO;

public interface LoanBLService {

    //LoanAllController
    ArrayList<String> getContract(String loanID);

    ResultMessage ifContract(String loanID, String url, String userid, Boolean ifPass);

    ResultMessage saveGovernmentSign(String loanID, String userid, int gov, String url);

    ArrayList<String> getSigns(String loanID);

    ArrayList<LoanVO> getPatentList(String userID);

    boolean getIfContract(String loanID,String userID);

    String getLatestLoanID(String patentID);

    //LoanApplicationController
    ResultMessage chooseInsurance(String loanID,String url,String insurance);

    String getPolicy(String loanID);

    ResultMessage chooseBank(String loanID, String url, double money, String time, String bank);

    String getApplicationToBank(String loanID);

    String saveLoanApply(String userID,String patentID);

    boolean ifValue(String patentID);

    boolean ifBankChosen(String loanID);

    //LoanInsuranceController
    ResultMessage insuranceApplication(String loanID, String url,String person, String address, String time, String reason,
                                       String bank, String bankName, String bankID, String insuranceID);

    ResultMessage ifInsurance(String loanID, boolean ifPass);

    ClaimVO getInsurance(String loanID);

    ResultMessage ifCompensate(String loanID, String claimID, boolean ifPass);

    //LoanBankController
    LoanVO getInfo(String loanID);

    LoanVO getApplication(String loanID);

    ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time);
}
