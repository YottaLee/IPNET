package com.ipnet.blservice;

import java.util.ArrayList;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.financevo.InsuranceVO;
import com.ipnet.vo.financevo.LoanVO;

public interface LoanBLService {

    /**
     * 获取该合同的所有方信息
     * @param loanID 贷款号
     * @return 0-专利持有人 1-金融机构 2-保险公司 3-评估机构 4-政府
     */
    ArrayList<String> getContract(String loanID);

    /**
     * 存取该合同的用户是否同意并签署最终合同
     * @param loanID 贷款号
     * @param userid 用户
     * @param isPass 是否同意
     * @return
     */
    ResultMessage ifContract(String loanID,String userid,boolean isPass);

    /**
     * 将该专利的质押贷款保证保险申请提供给保险公司
     *
     * @param loanID 贷款号
     * @param insurance 选择的保险公司
     * @return ResultMessage
     */
    ResultMessage chooseInsurance(String loanID,String insurance);

    /**
     * 存取该专利贷款意向结果
     *
     * @param loanID 贷款号
     * @param money 意向金额
     * @param time 意向期限
     * @param bank 金融机构
     * @return
     */
    ResultMessage chooseBank(String loanID,double money,String time,String bank);

    /**
     * 存取该专利已经有贷款申请，可借此机会生成loanID
     * @param patentID 专利号
     * @return 返回loanID
     */
    String saveLoanApply(String patentID);


    /**
     * 判断该专利是否已经拥有评估结果
     *
     * @param patentID 专利号
     * @return
     */
    boolean ifValue(String patentID);

    /**
     * 判断该专利是否已经填写贷款意向信息
     *
     * @param patentID
     * @return
     */
    boolean ifBankChosen(String patentID);



    ResultMessage insuranceApplication(String loanID, String person, String address, String time, String reason, String bank, String bankName, String bankID, String insuranceID, int money);

    ResultMessage ifInsurance(String loanID, boolean ifPass);

    InsuranceVO getInsurance(String loanID);

    ResultMessage ifCompensate(String loanID, String insuranceID, boolean ifPass);

    LoanVO getInfo(String loanID);

    LoanVO getApplication(String loanID);

    ResultMessage submitApplication(String loanID, String bank, boolean ifPass, boolean ifInsurance, int money, String time);
}
