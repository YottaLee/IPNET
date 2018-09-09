package com.ipnet.bl.loanbl;

import com.ipnet.blservice.LoanBLService;
import com.ipnet.dao.LoanDao;
import com.ipnet.dao.communitydao.InsuranceDao;
import com.ipnet.entity.Loan;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.InsuranceVO;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanBL implements LoanBLService {

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private InsuranceDao insuranceDao;

    @Override
    public ResultMessage insuranceApplication(String loanID, String person, String address, String time, String reason, String bank, String bankName, String bankID, String insuranceID, int money) {
        InsuranceVO insurance=new InsuranceVO(loanID,person,address,time,reason,bank,bankName,bankID,insuranceID,money);
        insuranceDao.saveAndFlush(insurance);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage ifInsurance(String loanID, boolean ifPass) {
        Loan loan=loanDao.getOne(loanID);
        loan.setAccept(ifPass);
        loanDao.save(loan);
        return ResultMessage.Success;
    }

    @Override
    public InsuranceVO getInsurance(String loanID) {
        InsuranceVO insuranceVO=insuranceDao.getOne(loanID);
        return insuranceVO;
    }

    @Override
    public ResultMessage ifCompensate(String loanID, String insuranceID, boolean ifPass) {
        Loan loan=loanDao.getOne(loanID);
        loan.setInsurance(insuranceID);
        loan.setToClaim(ifPass);
        loanDao.save(loan);
        return ResultMessage.Success;
    }

    @Override
    public LoanVO getInfo(String loanID) {
        Loan loan=loanDao.getOne(loanID);
        LoanVO loanVO=new LoanVO(loan);
        return loanVO;
    }

    @Override
    public LoanVO getApplication(String loanID) {
        Loan loan=loanDao.getOne(loanID);
        LoanVO loanVO=new LoanVO(loan);
        return loanVO;
    }

    @Override
    public ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time) {
        Loan loan=loanDao.getOne(loanID);
        loan.setBank(bank);
        loan.setPass(ifPass);
        loan.setAccept(ifInsurance);
        //这里有点疑问
        return ResultMessage.Success;
    }
}
