package com.ipnet.bl.loanbl;

import com.ipnet.bl.patentbl.PatentHelper;
import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.blservice.PatentBLService;
import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.loanblservice.LoanAllBLService;
import com.ipnet.blservice.loanblservice.LoanApplicantBLService;
import com.ipnet.blservice.loanblservice.LoanInsuranceBLService;
import com.ipnet.blservice.personalservice.UserInfoBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.InsuranceDao;
import com.ipnet.dao.LoanDao;
import com.ipnet.dao.PatentDao;
import com.ipnet.entity.Insurance;
import com.ipnet.entity.Loan;
import com.ipnet.entity.Patent;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.Patent_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.CreateInsuranceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class LoanApplicantBL implements LoanApplicantBLService {

    @Autowired
    private LoanDao loanDao;
    @Autowired
    private CompanyUserDao companyUserDao;
    @Autowired
    private PatentDao patentDao;
    @Autowired
    private EvaluationBLService evaluationBLService;
    @Autowired
    private PatentHelper patentHelper;
    @Autowired
    private UserBLService userBLService;
    @Autowired
    private UserInfoBLService userInfoBLService;
    @Autowired
    private LoanAllBLService loanAllBLService;
    @Autowired
    private LoanInsuranceBLService loanInsuranceBLService;

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
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setInsurance(insurance);
            loan.setInsuranceId(companyUserDao.findCompanyUserByName(insurance).getId());
            loan.setPolicy(url);
            loan.setState(Patent_loan_state.to_be_checked_by_insurance);
            loanDao.saveAndFlush(loan);
            CreateInsuranceVO createInsuranceVO = new CreateInsuranceVO(loanID, loan.getPolicy(), loan.getInsurance());
            loanInsuranceBLService.createInsurance(createInsuranceVO);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    /**
     * 获取保险申请文件的url
     *
     * @param loanID 贷款号
     * @return url
     */
    @Override
    public String getPolicy(String loanID) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        return loanOptional.map(Loan::getPolicy).orElse(null);
    }

    /**
     * 存取该专利贷款意向结果
     *
     * @param patentID 贷款号
     * @param url      意向申请的文件路径
     * @param money    意向金额
     * @param time     意向期限
     * @param bank     金融机构
     * @return ResultMessage
     */
    @Override
    public String chooseBank(String patentID, String userID, String url, double money, String time, String way, String bank) {
        String loanID = patentID;
        String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        loanID = loanID + currentTime;
        Loan loan = new Loan();
        loan.setLoanID(loanID);
        loan.setPatentID(patentID);
        loan.setUserID(userID);
        loan.setWay(way);
        loan.setBank(bank);
        loan.setBankId(companyUserDao.findCompanyUserByName(bank).getId());
        loan.setExpect_money(money);
        loan.setExpect_time(time);
        loan.setApplication(url);
        loan.setState(Patent_loan_state.to_be_checked_by_bank);
        loan.setPerson(userInfoBLService.getUserInfo(userID, userBLService.getUserRole(userID)).getName());
        loan.setEvaluation(evaluationBLService.getValue(patentID));
        String patentName = null;
        try {
            patentName = patentHelper.receivePatentName(patentID);
        } catch (IDNotExistsException e) {
            e.printStackTrace();
        }
        loan.setPatent(patentName);
        Patent patent = patentDao.getOne(patentID);
        patent.setState(Patent_state.to_be_loan);
        patentDao.saveAndFlush(patent);
        loanDao.saveAndFlush(loan);
        return loanID;
    }

    /**
     * 获取贷款意向文件的url
     *
     * @param loanID 贷款号
     * @return url
     */
    @Override
    public String getApplicationToBank(String loanID) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        return loanOptional.map(Loan::getApplication).orElse(null);
    }

    /**
     * 存取该专利已经有贷款申请，可借此机会生成loanID
     *
     * @param userID   用户ID
     * @param patentID 专利号
     * @return 返回loanID
     */
    @Override
    public String saveLoanApply(String userID, String patentID) {
        String loanID = patentID;
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        loanID = loanID + time;
        Loan loan = new Loan();
        loan.setLoanID(loanID);
        loan.setPatentID(patentID);
        loan.setPerson(userID);
        loan.setTime(time);
        loan.setEvaluation(evaluationBLService.getValue(patentID));
        String patentName = null;
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
     *
     * @param patentID 专利号
     * @return boolean
     */
    @Override
    public boolean ifValue(String patentID) {
        return evaluationBLService.ifValue(patentID);
    }

    /**
     * 判断该专利是否已经填写贷款意向信息
     *
     * @param loanID 贷款号
     * @return boolean
     */
    @Override
    public boolean ifBankChosen(String loanID) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            if (loan.getBank() == null || loan.getBank().equals("")) {
                return false;
            } else {
                loanAllBLService.changeState(loanID, Patent_loan_state.to_be_evaluation);
                return true;
            }
        }
        return false;
    }

    /**
     * 贷款到评估
     * @param loanID
     * @return
     */
    @Override
    public ResultMessage changeEvaluationState(String loanID) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setState(Patent_loan_state.to_be_evaluation);
            loanDao.saveAndFlush(loan);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    /**
     * 成功购买了保险
     * @param loanID
     * @return
     */
    @Override
    public String successPayForInsurance(String loanID) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setState(Patent_loan_state.to_be_final_confirm);
            loanDao.saveAndFlush(loan);
            try {
                return loanInsuranceBLService.getInsurance(loanID).getId();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 申请贷款成功
     * @param loanID
     * @return
     */
    @Override
    public String loanSuccess(String loanID,String transactionId){
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setState(Patent_loan_state.loaning);
            loan.setTransactionId(transactionId);
            loanDao.saveAndFlush(loan);
            Patent patent = patentDao.getOne(loan.getPatentID());
            patent.setState(Patent_state.loaning);
            patentDao.saveAndFlush(patent);
            try {
                return loanInsuranceBLService.getInsurance(loanID).getId();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "";
    }
}
