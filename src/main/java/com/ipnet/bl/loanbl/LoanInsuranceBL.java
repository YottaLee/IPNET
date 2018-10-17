package com.ipnet.bl.loanbl;

import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.loanblservice.LoanInsuranceBLService;
import com.ipnet.dao.InsuranceDao;
import com.ipnet.dao.LoanDao;
import com.ipnet.entity.Insurance;
import com.ipnet.entity.Loan;
import com.ipnet.enums.IfPass;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.CreateInsuranceVO;
import com.ipnet.vo.financevo.InsuranceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class LoanInsuranceBL implements LoanInsuranceBLService{
    @Autowired
    private InsuranceDao insuranceDao;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private EvaluationBLService evaluationBLService;

    @Override
    public ResultMessage createInsurance(CreateInsuranceVO createInsuranceVO) {
        SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd-HHmmss");
        String id=df.format(new Date());
        Loan loan=loanDao.getOne(createInsuranceVO.getLoan_id());
        Insurance insurance=new Insurance(id,createInsuranceVO.getLoan_id(),loan.getPatentID(),createInsuranceVO.getInsurance_url(),loan.getPerson(),loan.getInsurance(),new Date(),loan.getExpect_money(), IfPass.WAIT,evaluationBLService.getEvaluator().getId(),loan.getBank());
        insuranceDao.saveAndFlush(insurance);
        return ResultMessage.Success;
    }

    @Override
    public InsuranceVO getInsurance(String loanid) throws IDNotExistsException {
        //不确定
        String id=loanDao.getOne(loanid).getInsurance();
        Insurance insurance=insuranceDao.getOne(id);
        return new InsuranceVO(insurance);
    }

    @Override
    public ResultMessage ifInsurance(String id, IfPass ifPass) {
        Insurance insurance=insuranceDao.getOne(id);
        insurance.setIfPass(ifPass);
        insuranceDao.saveAndFlush(insurance);
        return ResultMessage.Success;
    }

    @Override
    public ArrayList<InsuranceVO> showListForInsurance(String id) throws IDNotExistsException {
        ArrayList<Insurance> insurances=insuranceDao.getInsuranceList(id);
        ArrayList<InsuranceVO> insuranceVOS=new ArrayList<>();
        for(Insurance i:insurances){
            insuranceVOS.add(new InsuranceVO(i));
        }
        return insuranceVOS;
    }
}
