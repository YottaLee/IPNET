package com.ipnet.blservice.loanblservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.LoanVO;

public interface LoanBankBLService {

    //获取贷款信息
    LoanVO getInfo(String loanID);

    //获取贷款意向
    LoanVO getApplication(String loanID);

    //银行提交通过意见
    ResultMessage submitApplication(String loanID, boolean ifPass, boolean ifInsurance, int money, String time);
}
