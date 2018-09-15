package com.ipnet.bl.loanbl;

import com.ipnet.blservice.loanblservice.LoanBankBLService;
import com.ipnet.dao.LoanDao;
import com.ipnet.entity.Loan;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanBankBL implements LoanBankBLService {

    @Autowired
    private LoanDao loanDao;

    //获取贷款信息
    @Override
    public LoanVO getInfo(String loanID) {
        Loan loan=loanDao.getOne(loanID);
        LoanVO loanVO=new LoanVO(loan);
        return loanVO;
    }

    //获取贷款意向
    @Override
    public LoanVO getApplication(String loanID) {
        Loan loan=loanDao.getOne(loanID);
        LoanVO loanVO=new LoanVO(loan);
        loanVO.setMoney(loan.getAccept_money());
        loanVO.setTime(loan.getAccept_time());
        return loanVO;
    }

    //银行提交通过意见
    @Override
    public ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time) {
        Loan loan=loanDao.getOne(loanID);
        loan.setBank(bank);
        loan.setBankPass(ifPass);
        loan.setIfInsurance(ifInsurance);
        loan.setAccept_money(money);
        loan.setTime(time);
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }
}
