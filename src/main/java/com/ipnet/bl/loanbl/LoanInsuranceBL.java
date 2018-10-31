package com.ipnet.bl.loanbl;

import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.loanblservice.LoanBankBLService;
import com.ipnet.blservice.loanblservice.LoanInsuranceBLService;
import com.ipnet.dao.InsuranceDao;
import com.ipnet.dao.LoanDao;
import com.ipnet.dao.PatentDao;
import com.ipnet.entity.Insurance;
import com.ipnet.entity.Loan;
import com.ipnet.entity.Patent;
import com.ipnet.enums.IfPass;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.Patent_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.CreateInsuranceVO;
import com.ipnet.vo.financevo.InsuranceVO;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class LoanInsuranceBL implements LoanInsuranceBLService {
    @Autowired
    private InsuranceDao insuranceDao;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private PatentDao patentDao;

    @Autowired
    private EvaluationBLService evaluationBLService;

    @Autowired
    private UserBLService userBLService;

    @Override
    public ResultMessage createInsurance(CreateInsuranceVO createInsuranceVO) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String id = df.format(new Date());
        Loan loan = loanDao.getOne(createInsuranceVO.getLoan_id());
        Insurance insurance = new Insurance(id, createInsuranceVO.getLoan_id(), loan.getPatentID(), createInsuranceVO.getInsurance_url(), loan.getPerson(), loan.getInsurance(), new Date(), loan.getExpect_money(), IfPass.WAIT, evaluationBLService.getEvaluator().getId(), loan.getBank());
        insuranceDao.saveAndFlush(insurance);
        return ResultMessage.Success;
    }

    @Override
    public InsuranceVO getInsurance(String loanid) throws IDNotExistsException {
        //不确定
        Insurance insurance = insuranceDao.getInsurance(loanid);
        return new InsuranceVO(insurance);
    }

    @Override
    public ResultMessage ifInsurance(String loanId, boolean ifPass) {
        Loan loan = loanDao.getOne(loanId);
        if (ifPass)
            loan.setState(Patent_loan_state.to_be_buy_insurance);
        else {
            Patent patent = patentDao.getOne(loan.getPatentID());
            patent.setState(Patent_state.free);
            patentDao.saveAndFlush(patent);
            loan.setState(Patent_loan_state.free);
        }
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }

    @Override
    public ArrayList<InsuranceVO> showListForInsurance(String id) throws IDNotExistsException {
        ArrayList<Insurance> insurances = insuranceDao.getInsuranceList(id);
        ArrayList<InsuranceVO> insuranceVOS = new ArrayList<>();
        for (Insurance i : insurances) {
            insuranceVOS.add(new InsuranceVO(i));
        }
        return insuranceVOS;
    }

    @Override
    public ResultMessage hasInsurance(String loanId) throws IDNotExistsException {
        Insurance insurance = insuranceDao.getInsurance(loanId);
        if (insurance != null)
            return ResultMessage.Success;
        else
            return ResultMessage.Fail;
    }

    @Override
    public ResultMessage overDue(String loanId) throws IDNotExistsException {
        Loan loan = loanDao.getOne(loanId);
        loan.setState(Patent_loan_state.overdue);
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }
}
