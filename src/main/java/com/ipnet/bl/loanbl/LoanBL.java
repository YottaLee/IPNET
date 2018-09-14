package com.ipnet.bl.loanbl;


import com.ipnet.blservice.LoanBLService;
import com.ipnet.dao.InsuranceDao;
import com.ipnet.dao.LoanDao;
import com.ipnet.entity.Loan;
import com.ipnet.enums.Patent_loan_state;
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
    public ResultMessage insuranceApplication(String insurance_contractid,String loanID, String url,String person, String address, String time, String reason,
                                              String bank, String bankName, String bankID, String insuranceID, int money) {

        InsuranceVO insurance=new InsuranceVO(insurance_contractid,loanID,url, Patent_loan_state.to_be_compensation_by_insurance,person,address,time,reason,bank,bankName,bankID,insuranceID,money);
        insuranceDao.saveAndFlush(insurance);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage ifInsurance(String loanID, boolean ifPass) {
        Loan loan=loanDao.getOne(loanID);
        loan.setInsurancePass(ifPass);
        loanDao.save(loan);
        return ResultMessage.Success;
    }

    /**
     * @Author: Jane
     * @Description: 获取金融机构的保险申请信息
     * @Date: 2018/9/12 11:48
     */

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

    /**
     * @Author: Jane
     * @Description: 获取贷款信息
     * @Date: 2018/9/12 11:49
     */
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
        loanVO.setMoney(loan.getExpect_money());
        loanVO.setTime(loan.getExpect_time());
        return loanVO;
    }

    @Override
    public ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time) {
        Loan loan=loanDao.getOne(loanID);
        loan.setBank(bank);
        loan.setBankPass(ifPass);
        loan.setIfInsurance(ifInsurance);
        loan.setAccept_money(money);
        loan.setAccept_time(time);
        loanDao.saveAndFlush(loan);
        return ResultMessage.Success;
    }
}
