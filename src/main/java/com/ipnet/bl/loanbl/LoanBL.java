package com.ipnet.bl.loanbl;

import com.ipnet.blservice.LoanBLService;
import com.ipnet.dao.InsuranceDao;
import com.ipnet.dao.LoanDao;
import com.ipnet.entity.Loan;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.InsuranceVO;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class LoanBL implements LoanBLService {

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private InsuranceDao insuranceDao;
    /**
     * 获取该合同的所有方信息
     * @param loanID 贷款号
     * @return 0-专利持有人 1-金融机构 2-保险公司 3-评估机构 4-政府
     */
    @Override
    public ArrayList<String> getContract(String loanID) {
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            ArrayList<String> info=new ArrayList<>();
            info.add(loan.getPerson());
            info.add(loan.getBank());
            info.add(loan.getInsurance());
            //评估机构的名字
            String evaluation = "评估机构";
            info.add(evaluation);
            info.add("");
            return info;
        }
        return null;
    }

    /**
     * 存取该合同的用户是否同意并签署最终合同
     * @param loanID 贷款号
     * @param userid 用户
     * @param isPass 是否同意
     * @return
     */
    @Override
    public ResultMessage ifContract(String loanID, String userid, boolean isPass) {

        return null;
    }

    @Override
    public ResultMessage chooseInsurance(String loanID, String insurance) {
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            loan.setInsurance(insurance);
            loanDao.saveAndFlush(loan);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    @Override
    public ResultMessage chooseBank(String loanID, double money, String time, String bank) {
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            loan.setBank(bank);
            loan.setExpect_money(money);
            loan.setExpect_time(time);
            loanDao.saveAndFlush(loan);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    @Override
    public String saveLoanApply(String patentID) {
        String loanID=patentID;
        String time=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        loanID=loanID+time;
        Loan loan=new Loan();
        loan.setLoanID(loanID);
        loanDao.save(loan);
        return loanID;
    }

    @Override
    public boolean ifValue(String patentID) {
        return false;
    }

    @Override
    public boolean ifBankChosen(String patentID) {
        Optional<Loan> loanOptional=loanDao.findById(patentID);
        return loanOptional.map(loan -> loan.getBank().equals("")).orElse(false);
    }


    @Override
    public ResultMessage insuranceApplication(String insurance_contractid,String loanID, String person, String address, String time, String reason, String bank, String bankName, String bankID, String insuranceID, int money) {
        InsuranceVO insurance=new InsuranceVO(insurance_contractid,loanID,person,address,time,reason,bank,bankName,bankID,insuranceID,money);
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
