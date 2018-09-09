package com.ipnet.blservice;


import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.InsuranceVO;
import com.ipnet.vo.financevo.LoanVO;

public interface LoanBLService {

    ResultMessage insuranceApplication(String insurance_contractid,String loanID, String person, String address, String time, String reason, String bank, String bankName, String bankID, String insuranceID, int money);

    ResultMessage ifInsurance(String loanID, boolean ifPass);

    InsuranceVO getInsurance(String loanID);

    ResultMessage ifCompensate(String loanID, String insuranceID, boolean ifPass);

    LoanVO getInfo(String loanID);

    LoanVO getApplication(String loanID);

    ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time);
}
