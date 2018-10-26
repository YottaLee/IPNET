package com.ipnet.bl.loanbl;

import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.loanblservice.LoanAllBLService;
import com.ipnet.dao.LoanDao;
import com.ipnet.entity.Loan;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.vo.financevo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoanAllBL implements LoanAllBLService {


    @Autowired
    private LoanDao loanDao;
    @Autowired
    private UserBLService userBLService;

    /**
     * 获取该合同的所有方信息
     *
     * @param loanID 贷款号
     * @return 0-专利持有人 1-金融机构 2-保险公司 3-评估机构 4-政府
     */
    @Override
    public ArrayList<String> getContract(String loanID) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            ArrayList<String> info = new ArrayList<>();
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
     *
     * @param loanID 贷款号
     * @param url    上传签字的url
     * @param userid 用户
     * @param ifPass 是否同意
     * @return ResultMesage
     */
    @Override
    public ResultMessage ifContract(String loanID, String url, String userid, Boolean ifPass) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            Role role = userBLService.getUserRole(userid);
            switch (role) {
                case Insurance:
                    loan.setInsurancePass(ifPass);
                    loan.setInsurancesign(url);
                    break;
                case Financial:
                    loan.setBankPass(ifPass);
                    loan.setBanksign(url);
                    break;
                case Evaluator:
                    loan.setEvaluationPass(ifPass);
                    loan.setEvaluationsign(url);
                    break;
                default:
                    loan.setOwnerPass(ifPass);
            }
            if (loan.isOwnerPass() && loan.isBankPass() && loan.isEvaluationPass() && loan.isInsurancePass()) {
                loan.setState(Patent_loan_state.to_be_buy_insurance);
            }
            loanDao.save(loan);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    /**
     * 该用户是否已在该合同最终确认（此步接上个controller）
     *
     * @param loanID 贷款号
     * @param userid 用户ID
     * @return 是否确认过合同
     */
    @Override
    public boolean getIfContract(String loanID, String userid) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            Role role = userBLService.getUserRole(userid);
            switch (role) {
                case Insurance:
                    return loan.isInsurancePass();
                case Evaluator:
                    return loan.isEvaluationPass();
                case Financial:
                    return loan.isBankPass();
                default:
                    return loan.isOwnerPass();
            }
        }
        return false;
    }

    /**
     * 根据专利号得到最近一次质押号
     *
     * @param patentID 专利号
     * @return 最近一次质押号，如果没有质押记录，返回null
     */
    @Override
    public String getLatestLoanID(String patentID) {
        ArrayList<Loan> loans = loanDao.findByPatentIDSortByTime(patentID);
        if (loans == null || loans.size() == 0) {
            return null;
        } else {
            return loans.get(0).getLoanID();
        }
    }

    /**
     * 存取合同url
     *
     * @param loanID 贷款ID
     * @param userid 用户ID
     * @param gov    如果用户为专利持有人，此参数有效，0为知识产权局，1为财政局
     * @param url    url
     * @return ResultMessage
     */
    @Override
    public ResultMessage saveGovernmentSign(String loanID, String userid, int gov, String url) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            if (loan.getPerson().equals(userid)) {
                if (gov == 0) {
                    loan.setIposign(url);
                } else {
                    loan.setFinancesign(url);
                }
                if (loan.getIposign() != null && loan.getFinancesign() != null) {
//                    loan.setState(Patent_loan_state.to_be_contract);
                }
                loanDao.save(loan);
                return ResultMessage.Success;
            } else {
                return ResultMessage.Fail;
            }
        }
        return ResultMessage.Fail;
    }

    /**
     * 获取合同照片的url
     *
     * @param loanID 贷款号
     * @return 0-知识产权局 1-财政局 2-金融机构 3-保险公司 4-评估机构, 如果没有该项返回""
     */
    @Override
    public ArrayList<String> getSigns(String loanID) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            ArrayList<String> signs = new ArrayList<>();
            signs.add(loan.getIposign());
            signs.add(loan.getFinancesign());
            signs.add(loan.getBanksign());
            signs.add(loan.getInsurancesign());
            signs.add(loan.getEvaluationsign());
            return signs;
        }
        return null;
    }

    /**
     * 返回金融机构、保险公司、评估机构有关的所有专利信息
     *
     * @param userID 用户id
     * @return 专利列表
     */
    @Override
    public ArrayList<LoanVO> getPatentList(String userID) {
        Role role = userBLService.getUserRole(userID);
        ArrayList<LoanVO> result = new ArrayList<>();
        ArrayList<Loan> loans = new ArrayList<>();
        switch (role) {
            case Evaluator:
                loans = loanDao.findAllSortByTime();
                break;
            case Insurance:
                loans = loanDao.findByInsuranceSortByTime(userID);
                break;
            case Financial:
                loans = loanDao.findByBankSortByTime(userID);
                break;
            default:
                break;
        }
        for (Loan loan : loans) {
            LoanVO vo = new LoanVO(loan);
//            vo.setMoney(loan.getAccept_money());
//            vo.setTime(loan.getAccept_time());
//            vo.setPerson(userBLService.getXingMing(loan.getPerson()));
            result.add(vo);
        }
        return result;
    }

    @Override
    public ResultMessage changeState(String loanID, Patent_loan_state state) {
        Optional<Loan> loanOptional = loanDao.findById(loanID);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setState(state);
            loanDao.saveAndFlush(loan);
            return ResultMessage.Success;
        }
        return null;
    }

    @Override
    public ResultMessage changeStateByPatentID(String patentID, Patent_loan_state state) {
        ArrayList<Loan> loans = loanDao.findByPatentID(patentID);
        for (Loan loan : loans) {
            if (loan.getState() != Patent_loan_state.free) {
                loan.setState(state);
                loanDao.saveAndFlush(loan);
            }
        }
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage changeEvaluationByPatentID(String patentID, double evaluation) {
        ArrayList<Loan> loans = loanDao.findByPatentID(patentID);
        for (Loan loan : loans) {
            loan.setEvaluation(evaluation);
        }
        loanDao.saveAll(loans);
        return ResultMessage.Success;
    }
}
