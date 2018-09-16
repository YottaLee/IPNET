package com.ipnet.bl.transcationbl;

import com.ipnet.blservice.AliService;
import com.ipnet.blservice.PatentBLService;
import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.blservice.transcationservice.TranscationBlService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.PatentDao;
import com.ipnet.dao.PersonalUserDao;
import com.ipnet.dao.contractdao.*;
import com.ipnet.entity.Patent;
import com.ipnet.entity.contract.*;
import com.ipnet.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class BuyAndSellBL implements TranscationBlService {

    @Autowired
    private PersonalUserDao userDao;
    @Autowired
    private CompanyUserDao companyUserDao;
    @Autowired
    private AgentContractDao agentContractDao;
    @Autowired
    private BreakupContractDao breakupContractDao;
    @Autowired
    private LoanContractDao loanContractDao;
    @Autowired
    private PermitcontractDao permitcontractDao;
    @Autowired
    private TransferContractDao transferContractDao;
    @Autowired
    private PatentDao patentDao;
    @Autowired
    private AliService aliService;
    @Autowired
    private JavaMailSender mailSender;


    @Override
    public ResultMessage draftContract(Contract contract, TranscationType transcationType,ContractType contractType) {
        switch(contractType){
            case Loan:
                LoanContract loanContract=new LoanContract();
                loanContract.setParty_A(contract.getPartyA());
                loanContract.setParty_B(contract.getPartyB());
                loanContract.setAddressA(contract.getAddressA());
                loanContract.setAddressB(contract.getAddressB());
                loanContract.setContractType(ContractState.draft);
                loanContractDao.save(loanContract);
                return ResultMessage.Success;
            case Agent:
                AgentContract agentContract=new AgentContract();
                agentContract.setContractType(ContractState.draft);
                agentContractDao.save(agentContract);
                return ResultMessage.Success;
            case Permit:
                PermitContract permitContract=new PermitContract();
                permitcontractDao.save(permitContract);
                return ResultMessage.Success;
            case Breakup:
                BreakupContract breakupContract=new BreakupContract();
                breakupContract.setContractType(ContractState.draft);
                breakupContractDao.save(breakupContract);
                return ResultMessage.Success;
            case Transfer:
                TransferContract transferContract=new TransferContract();
                transferContract.setContractType(ContractState.draft);
                transferContractDao.save(transferContract);
                return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage confirmContract(String contract_id, ContractType contractType) {
        switch(contractType){
            case Loan:
                LoanContract loanContract=loanContractDao.getOne(contract_id);
                loanContract.setContractType(ContractState.confirm);
                loanContractDao.save(loanContract);
                return ResultMessage.Success;
            case Agent:
                AgentContract agentContract=agentContractDao.getOne(contract_id);
                agentContract.setContractType(ContractState.confirm);
                agentContractDao.save(agentContract);
                return ResultMessage.Success;
            case Permit:
                PermitContract permitContract=permitcontractDao.getOne(contract_id);
                return ResultMessage.Success;
            case Breakup:
                BreakupContract breakupContract=breakupContractDao.getOne(contract_id);
                return ResultMessage.Success;
            case Transfer:
                TransferContract transferContract=transferContractDao.getOne(contract_id);
                transferContract.setContractType(ContractState.confirm);
                transferContractDao.save(transferContract);
                return ResultMessage.Success;
        }
        return ResultMessage.Fail ;
    }

    @Override
    public ResultMessage payContract(String contract_id, double rmb, ContractType contractType) {
        switch(contractType){
            case Loan:
                LoanContract loanContract=loanContractDao.getOne(contract_id);
                return ResultMessage.Success;
            case Agent:
                AgentContract agentContract=agentContractDao.getOne(contract_id);
                return ResultMessage.Success;
            case Permit:
                PermitContract permitContract=permitcontractDao.getOne(contract_id);
                return ResultMessage.Success;
            case Breakup:
                BreakupContract breakupContract=breakupContractDao.getOne(contract_id);
                return ResultMessage.Success;
            case Transfer:
                TransferContract transferContract=transferContractDao.getOne(contract_id);
                return ResultMessage.Success;
        }
        return ResultMessage.Fail ;
    }

    @Override
    public ResultMessage completeTranscation(String contract_id, TranscationType transcationType, ContractType contractType) {
        Patent patent;
        switch(contractType){
            case Loan:
                LoanContract loanContract=loanContractDao.getOne(contract_id);
                loanContract.setContractType(ContractState.complete);
                loanContractDao.save(loanContract);
                patent=patentDao.getOne(loanContract.getContract_id());
                patent.setState(Patent_state.free);
                patentDao.save(patent);
                return ResultMessage.Success;
            case Agent:
                AgentContract agentContract=agentContractDao.getOne(contract_id);
                agentContract.setContractType(ContractState.complete);
                agentContractDao.save(agentContract);
                patent=patentDao.getOne(agentContract.getId_of_patent());
                patent.setState(Patent_state.free);
                patentDao.save(patent);
                return ResultMessage.Success;
            case Permit:
                PermitContract permitContract=permitcontractDao.getOne(contract_id);
                patent=patentDao.getOne(permitContract.getPatent_id());
                patent.setState(Patent_state.free);
                patentDao.save(patent);
                return ResultMessage.Success;
            case Breakup:

                return ResultMessage.Success;
            case Transfer:
                TransferContract transferContract=transferContractDao.getOne(contract_id);
                patent=patentDao.getOne(transferContract.getPatent_id());
                patent.setState(Patent_state.free);
                patentDao.save(patent);
                return ResultMessage.Success;
        }
        return ResultMessage.Fail ;
    }
}
