package com.ipnet.blservice.loanblservice;

import com.ipnet.enums.IfPass;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.CreateInsuranceVO;
import com.ipnet.vo.financevo.InsuranceVO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

public interface LoanInsuranceBLService {

    ResultMessage createInsurance(CreateInsuranceVO createInsuranceVO);

    //获取保险信息
    InsuranceVO getInsurance(String loanid) throws IDNotExistsException;

    //是否愿意投保
    ResultMessage ifInsurance(String loanId, boolean ifPass);

    //保险公司查看列表
    ArrayList<InsuranceVO> showListForInsurance(String id) throws IDNotExistsException;

    ResultMessage hasInsurance( String loanId)  throws IDNotExistsException;

    ResultMessage overDue( String loanId) throws IDNotExistsException;

}
