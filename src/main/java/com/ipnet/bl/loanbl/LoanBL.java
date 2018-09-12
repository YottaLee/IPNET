package com.ipnet.bl.loanbl;

import com.ipnet.bl.patentbl.PatentHelper;
import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.blservice.LoanBLService;
import com.ipnet.blservice.PatentBLService;
import com.ipnet.blservice.UserBLService;
import com.ipnet.dao.InsuranceDao;
import com.ipnet.dao.LoanDao;
import com.ipnet.entity.Loan;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.Role;
import com.ipnet.utility.IDNotExistsException;
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
    @Autowired
    private UserBLService userBLService;
    @Autowired
    private PatentBLService patentBLService;
    @Autowired
    private EvaluationBLService evaluationBLService;
    @Autowired
    private PatentHelper patentHelper;

    //LoanAllController

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
     * @param url    上传签字的url
     * @param userid 用户
     * @param ifPass 是否同意
     * @return ResultMesage
     */
    @Override
    public ResultMessage ifContract(String loanID, String url, String userid, Boolean ifPass) {
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if (loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            Role role=userBLService.getUserRole(userid);
            switch (role){
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
            loanDao.save(loan);
            return ResultMessage.Success;
        }
        return ResultMessage.Fail;
    }

    /**
     * 该用户是否已在该合同最终确认（此步接上个controller）
     * @param loanID 贷款号
     * @param userid 用户ID
     * @return 是否确认过合同
     */
    @Override
    public boolean getIfContract(String loanID,String userid){
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            Role role=userBLService.getUserRole(userid);
            switch (role){
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
     * @param patentID 专利号
     * @return 最近一次质押号，如果没有质押记录，返回null
     */
    @Override
    public String getLatestLoanID(String patentID){
        ArrayList<Loan> loans=loanDao.findByPatentIDSortByTime(patentID);
        if(loans==null || loans.size()==0){
            return null;
        }else {
            return loans.get(0).getLoanID();
        }
    }

    /**
     * 存取合同url
     * @param loanID 贷款ID
     * @param userid 用户ID
     * @param gov 如果用户为专利持有人，此参数有效，0为知识产权局，1为财政局
     * @param url url
     * @return ResultMessage
     */
    @Override
    public ResultMessage saveGovernmentSign(String loanID, String userid, int gov, String url){
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            if(loan.getPerson().equals(userid)){
                if(gov==0){
                    loan.setIposign(url);
                }else{
                    loan.setFinancesign(url);
                }
                loanDao.save(loan);
                return ResultMessage.Success;
            }else {
                return ResultMessage.Fail;
            }
        }
        return ResultMessage.Fail;
    }

    /**
     * 获取合同照片的url
     * @param loanID 贷款号
     * @return 0-知识产权局 1-财政局 2-金融机构 3-保险公司 4-评估机构, 如果没有该项返回""
     */
    @Override
    public ArrayList<String> getSigns(String loanID){
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            ArrayList<String> signs=new ArrayList<>();
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
     * @param userID 用户id
     * @return 专利列表
     */
    @Override
    public ArrayList<LoanVO> getPatentList(String userID){
        Role role=userBLService.getUserRole(userID);
        ArrayList<LoanVO> result=new ArrayList<>();
        ArrayList<Loan> loans = new ArrayList<>();
        switch (role){
            case Evaluator:
                loans=loanDao.findAllSortByTime();
                break;
            case Insurance:
               loans=loanDao.findByInsuranceSortByTime(userID);
               break;
            case Financial:
                loans=loanDao.findByBankSortByTime(userID);
                break;
            default:
                break;
        }
        for(Loan loan:loans){
            LoanVO vo=new LoanVO(loan);
            vo.setMoney(loan.getAccept_money());
            vo.setTime(loan.getAccept_time());
            result.add(vo);
        }
        return result;
    }

    //LoanApplicationController

    /**
     * 将该专利的质押贷款保证保险申请提供给保险公司
     *
     * @param loanID    贷款号
     * @param url       专利质押保险投保单url
     * @param insurance 选择的保险公司
     * @return ResultMessage
     */
    @Override
    public ResultMessage chooseInsurance(String loanID, String url,String insurance) {
        Optional<Loan> loanOptional=loanDao.findById(loanID);
        if(loanOptional.isPresent()){
            Loan loan=loanOptional.get();
            loan.setInsurance(insurance);
            loan.setPolicy(url);
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
        String patentName= null;
        try {
            patentName = patentHelper.receivePatentName(patentID);
        } catch (IDNotExistsException e) {
            e.printStackTrace();
        }
        loan.setPatent(patentName);
        loanDao.save(loan);
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
        return loanOptional.map(loan -> loan.getBank().equals("")||loan.getBank()==null).orElse(false);
    }


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
