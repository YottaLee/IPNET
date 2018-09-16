package com.ipnet.bl.loanbl;

import com.ipnet.blservice.loanblservice.LoanBankBLService;
import com.ipnet.dao.LoanDao;
import com.ipnet.entity.Loan;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanBankBL implements LoanBankBLService {

    @Autowired
    private LoanDao loanDao;

    /**
     * 获取贷款信息
     *
     * @param loanID 贷款号
     * @return 贷款信息
     */
    @Override
    public LoanVO getInfo(String loanID) {
        System.out.println(loanID);
        Loan loan = loanDao.getOne(loanID);
        return new LoanVO(loan);
    }

    /**
     * 获取贷款意向
     *
     * @param loanID 贷款号
     * @return 贷款意向
     */
    @Override
    public LoanVO getApplication(String loanID) {
        Loan loan = loanDao.getOne(loanID);
        LoanVO loanVO = new LoanVO(loan);
        loanVO.setMoney(loan.getExpect_money());
        loanVO.setTime(loan.getExpect_time());
        loanVO.setPerson(loan.getPerson());
        loanVO.setEvaluation(loan.getEvaluation());
        return loanVO;
    }

    /**
     * 银行提交通过意见
     *
     * @param loanID      贷款号
     * @param bank        金融机构名称
     * @param ifPass      是否同意放贷
     * @param ifInsurance 是否同意让专利持有人购买专利质押贷款保证保险
     * @param money       放贷金额
     * @param time        放贷期限
     * @return ResultMessage
     */
    @Override
    public ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time) {
        Loan loan = loanDao.getOne(loanID);
        loan.setBank(bank);
        loan.setBankPass(ifPass);
        if (!ifPass) {//银行不同意放贷，此次申请就此结束
            loan.setState(Patent_loan_state.free);
        } else if (ifInsurance) {//如果银行需要专利持有人购买专利质押贷款保证保险
            loan.setState(Patent_loan_state.to_be_choose_insurance);
        } else {//银行不需要专利持有人购买专利质押贷款保证保险，但是这种情况应该不存在
            loan.setState(Patent_loan_state.to_be_contract);
        }
        loan.setIfInsurance(ifInsurance);
        loan.setAccept_money(money);
        loan.setTime(time);
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }
}
