package com.ipnet.blservice.loanblservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.InsuranceVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public interface LoanInsuranceBLService {

    ResultMessage createInsurance(InsuranceVO insuranceVO);

    //获取保险信息
    InsuranceVO getInsurance(String loanid) throws IDNotExistsException;

    //是否愿意投保
    ResultMessage ifInsurance(String id, boolean ifPass);

    //保险公司查看列表
    ArrayList<InsuranceVO> showListForInsurance(@RequestParam String id) throws IDNotExistsException;


}
