package com.ipnet.bl.loanbl;

import com.ipnet.blservice.loanblservice.LoanBankBLService;
import com.ipnet.dao.LoanDao;
import com.ipnet.dao.PatentDao;
import com.ipnet.entity.Loan;
import com.ipnet.entity.Patent;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.Patent_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanBankBL implements LoanBankBLService {

    @Autowired
    private LoanDao loanDao;
    @Autowired
    private PatentDao patentDao;

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

        LoanVO loanVO = new LoanVO(loan);
        loanVO.setTime(loan.getTime());
        loanVO.setMoney(loan.getAccept_money());
        return loanVO;
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
        return loanVO;
    }

    /**
     * 银行提交通过意见
     *
     * @param loanID 贷款号
     * @param ifPass 是否同意放贷
     * @return ResultMessage
     */
    @Override
    public ResultMessage submitApplication(String loanID, boolean ifPass) {
        Loan loan = loanDao.getOne(loanID);
        loan.setBankPass(ifPass);
        if (!ifPass) { //银行不同意放贷，此次申请就此结束
            loan.setState(Patent_loan_state.free);
            Patent patent = patentDao.getOne(loan.getPatentID());
            patent.setState(Patent_state.free);
            patentDao.saveAndFlush(patent);
        }
        else
            loan.setState(Patent_loan_state.to_be_value);
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }

    /**
     * 银行提交中期审核
     *
     * @param loanID      贷款号
     * @param ifPass      是否继续同意
     * @param ifInsurance 是否让专利持有人购买专利质押融资贷款保证保险
     * @return
     */
    @Override
    public ResultMessage submitMidApplication(String loanID, boolean ifPass, boolean ifInsurance) {
        Loan loan = loanDao.getOne(loanID);
        if (!ifPass) { //银行不同意放贷，此次申请就此结束
            loan.setState(Patent_loan_state.free);
            Patent patent = patentDao.getOne(loan.getPatentID());
            patent.setState(Patent_state.free);
            patentDao.saveAndFlush(patent);
        }
        else if(ifInsurance)
            loan.setState(Patent_loan_state.to_be_choose_insurance);
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }

    /**
     * 银行提交最终审核意见
     *
     * @param loanID 贷款号
     * @param ifPass 是否同意放贷
     * @return ResultMessage
     */
    @Override
    public ResultMessage submitDecision(String loanID, boolean ifPass){
        Loan loan = loanDao.getOne(loanID);
        if (!ifPass) { //银行不同意放贷，此次申请就此结束
            loan.setState(Patent_loan_state.free);
            Patent patent = patentDao.getOne(loan.getPatentID());
            patent.setState(Patent_state.free);
            patentDao.saveAndFlush(patent);
        }
        else
            loan.setState(Patent_loan_state.to_be_contract_by_loan);
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }

    /**
     * 银行提交协议
     * @param loanID 贷款号
     * @param loanMoney 贷款金额
     * @param returnMoreMoney 还款金额
     * @param duration 时长
     * @param compensation 赔偿金额
     * @return
     */
    @Override
    public ResultMessage hasContract(String loanID, double loanMoney, double returnMoreMoney, String duration, double compensation) {
        Loan loan = loanDao.getOne(loanID);
        loan.setAccept_money(loanMoney);
        loan.setAccept_time(duration);
        loan.setReturn_money(returnMoreMoney);
        loan.setCompensation(compensation);
        loan.setState(Patent_loan_state.to_be_contract_by_loan);
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }
}
