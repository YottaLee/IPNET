package com.ipnet.blservice.transcationservice;

import com.ipnet.entity.contract.Contract;
import com.ipnet.enums.ContractType;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.TranscationType;

/**
 * nan
 */
public interface TranscationBlService {

    //草拟合同
    ResultMessage draftContract(Contract contract, TranscationType transcationType, ContractType contractType);

    //确认合同
    ResultMessage confirmContract(String contract_id, ContractType contractType);

    // 付款
    ResultMessage payContract(String contract_id,double rmb, ContractType contractType);

    //交易专利使用权 专利交易完成之后 要更改对应的专利的信息 专利打包交易 要操作 池中的每一个专利
    ResultMessage completeTranscation (String contract_id,TranscationType transcationType, ContractType contractType);


}
