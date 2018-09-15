package com.ipnet.bl.loanbl;

import com.ipnet.blservice.loanblservice.LoanClaimBLService;
import com.ipnet.dao.ClaimDao;
import com.ipnet.entity.Claim;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.ClaimVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoanClaimBL implements LoanClaimBLService {

    @Autowired
    private ClaimDao claimDao;

    @Override
    public ArrayList<ClaimVO> showList(String id) {
        ArrayList<Claim> claims=claimDao.getList(id);
        ArrayList<ClaimVO> claimVOS=new ArrayList<>();
        for(Claim c:claims){
            claimVOS.add(new ClaimVO(c));
        }
        return claimVOS;
    }

    @Override
    public ResultMessage insurancePay(String id,double money) {
        Claim claim=claimDao.getOne(id);
        claim.setInsurance_money(money);
        //我不知道初始值是多少
        if(claim.getEvaluate_moey()!=0){
            claim.setLoan_state(Patent_loan_state.free);
        }
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage evaluationPay(String id,double money) {
        Claim claim=claimDao.getOne(id);
        claim.setEvaluate_moey(money);
        if(claim.getInsurance_money()!=0){
            claim.setLoan_state(Patent_loan_state.free);
        }
        return ResultMessage.Success;
    }
}
