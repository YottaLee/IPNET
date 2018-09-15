package com.ipnet.bl.loanbl;

import com.ipnet.blservice.loanblservice.LoanInsuranceBLService;
import com.ipnet.dao.InsuranceDao;
import com.ipnet.entity.Insurance;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.InsuranceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class LoanInsuranceBL implements LoanInsuranceBLService{
    @Autowired
    private InsuranceDao insuranceDao;
    @Override
    public InsuranceVO getInsurance(String id) throws IDNotExistsException {
        Insurance insurance=insuranceDao.getOne(id);
        return new InsuranceVO(insurance);
    }

    @Override
    public ResultMessage ifInsurance(String id, boolean ifPass) {
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
