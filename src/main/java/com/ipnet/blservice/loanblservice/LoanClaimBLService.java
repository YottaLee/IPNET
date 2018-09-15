package com.ipnet.blservice.loanblservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.ClaimVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public interface LoanClaimBLService {

    ArrayList<ClaimVO> showList(@RequestParam String id);

    ResultMessage insurancePay(String id,double money);

    ResultMessage evaluationPay(String id,double money);
}
