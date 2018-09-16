package com.ipnet.bl.loanbl;

import com.ipnet.bl.patentbl.PatentHelper;
import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.blservice.loanblservice.LoanAllBLService;
import com.ipnet.blservice.loanblservice.LoanApplicantBLService;
import com.ipnet.dao.LoanDao;
import com.ipnet.entity.Loan;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class LoanApplicantBL implements LoanApplicantBLService{

    @Autowired
    private LoanDao loanDao;
    @Autowired
    private EvaluationBLService evaluationBLService;
    @Autowired
    private PatentHelper patentHelper;
    @Autowired
    private LoanAllBLService loanAllBLService;

    /**
     * 将该专利的质押贷款保证保险申请提供给保险公司
     *
     * @param loanID    贷款号
     * @param url       专利质押保险投保单url
     * @param insurance 选择的保险公司
     * @return ResultMessage
     */
    @Override
    public ResultMessage chooseInsurance(String loanID, String url, String insurance) {
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            loan.setInsurance(insurance);
            loan.setPolicy(url);
            loan.setState(Patent_loan_state.to_be_checked_by_insurance);
            loanDao.saveAndFlush(loan);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    /**
     * 获取保险申请文件的url
     * @param loanID 贷款号
     * @return url
     */
    @Override
    public String getPolicy(String loanID){
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        return loanOptional.map(Loan::getPolicy).orElse(null);
    }

    /**
     * 存取该专利贷款意向结果
     *
     * @param loanID 贷款号
     * @param url 意向申请的文件路径
     * @param money  意向金额
     * @param time   意向期限
     * @param bank   金融机构
     * @return ResultMessage
     */
    @Override
    public ResultMessage chooseBank(String loanID, String url,double money, String time, String bank) {
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            loan.setBank(bank);
            loan.setExpect_money(money);
            loan.setExpect_time(time);
            loan.setApplication(url);
            loan.setState(Patent_loan_state.to_be_checked_by_bank);
            loanDao.saveAndFlush(loan);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    /**
     * 获取贷款意向文件的url
     * @param loanID 贷款号
     * @return url
     */
    @Override
    public String getApplicationToBank(String loanID){
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        return loanOptional.map(Loan::getApplication).orElse(null);
    }

    /**
     * 存取该专利已经有贷款申请，可借此机会生成loanID
     * @param userID 用户ID
     * @param patentID 专利号
     * @return 返回loanID
     */
    @Override
    public String saveLoanApply(String userID,String patentID) {
        String loanID=patentID;
        String time=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        loanID=loanID+time;
        Loan loan=new Loan();
        loan.setLoanID(loanID);
        loan.setPatentID(patentID);
        loan.setPerson(userID);
        loan.setTime(time);
        loan.setEvaluation(evaluationBLService.getValue(patentID));
        String patentName= null;
        try {
            patentName = patentHelper.receivePatentName(patentID);
        } catch (IDNotExistsException e) {
            e.printStackTrace();
        }
        loan.setPatent(patentName);
        loan.setState(Patent_loan_state.to_be_value);
        loanDao.saveAndFlush(loan);
        return loanID;
    }

    /**
     * 判断该专利是否已经拥有评估结果
     * @param patentID 专利号
     * @return boolean
     */
    @Override
    public boolean ifValue(String patentID) {
        return evaluationBLService.ifValue(patentID);
    }

    /**
     * 判断该专利是否已经填写贷款意向信息
     * @param loanID 贷款号
     * @return boolean
     */
    @Override
    public boolean ifBankChosen(String loanID) {
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            if(loan.getBank()==null||loan.getBank().equals("")){
                return false;
            }else {
                loanAllBLService.changeState(loanID,Patent_loan_state.to_be_evaluation);
                return true;
            }
        }
        return false;
    }

    @Override
    public ResultMessage changeEvaluationState(String loanID){
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            loan.setState(Patent_loan_state.to_be_loan_application);
            loanDao.saveAndFlush(loan);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }
}
