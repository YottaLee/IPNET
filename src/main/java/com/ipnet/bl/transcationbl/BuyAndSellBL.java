package com.ipnet.bl.transcationbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.blservice.transcationservice.TranscationBlService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.PersonalUserDao;
import com.ipnet.entity.contract.Contract;
import com.ipnet.enums.ContractType;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.TranscationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class BuyAndSellBL implements TranscationBlService {

    @Autowired
    private PersonalUserDao userDao;
    @Autowired
    private CompanyUserDao companyUserDao;
    @Autowired
    private AliService aliService;
    @Autowired
    private JavaMailSender mailSender;


    @Override
    public ResultMessage draftContract(Contract contract, TranscationType transcationType,ContractType contractType) {
        switch(contractType){
            case Loan:
            case Agent:
            case Permit:
            case Breakup:
            case Transfer:
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage confirmContract(String contract_id, ContractType contractType) {
        switch(contractType){
            case Loan:
            case Agent:
            case Permit:
            case Breakup:
            case Transfer:
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage payContract(String contract_id, double rmb, ContractType contractType) {

        switch(contractType){
            case Loan:
            case Agent:
            case Permit:
            case Breakup:
            case Transfer:
        }return ResultMessage.Fail;
    }

    @Override
    public ResultMessage completeTranscation(String contract_id, TranscationType transcationType, ContractType contractType) {
        switch(contractType){
            case Loan:
            case Agent:
            case Permit:
            case Breakup:
            case Transfer:
        }
        return ResultMessage.Fail ;
    }
}
