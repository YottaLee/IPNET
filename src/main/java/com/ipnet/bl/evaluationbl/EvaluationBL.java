package com.ipnet.bl.evaluationbl;

import com.ipnet.bl.patentbl.PatentHelper;
import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.blservice.UserBLService;
import com.ipnet.blservice.loanblservice.LoanAllBLService;
import com.ipnet.dao.EvaluationDao;
import com.ipnet.entity.Evaluation;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.financevo.EvaluationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class EvaluationBL implements EvaluationBLService {
    @Autowired
    private EvaluationDao evaluationDao;
    @Autowired
    private UserBLService userBLService;
    @Autowired
    private PatentHelper patentHelper;
    @Autowired
    private LoanAllBLService loanAllBLService;

    /**
     * 获取平台唯一的评估机构name
     * @return 评估机构的name
     */
    @Override
    public  String getEvaluator(){
        return userBLService.getEvaluationName();
    }

    /**
     * 提交专利评估报告
     * @param patentID 专利号
     * @param url 上传评估报告的文件路径
     * @param evaluation 经济评估价值
     * @param money 评估费用
     * @return ResultMessage
     */
    @Override
    public ResultMessage submitReport(String patentID,String url,String rule,String tech, double evaluation,String result, double money) {
        ArrayList<Evaluation> evaluations=evaluationDao.findByPatentIDSortByTime(patentID);
        if(evaluations==null||evaluations.size()==0){
           return ResultMessage.Fail;
        }else {
            Evaluation toUpdate=evaluations.get(0);
            toUpdate.setReport(url);
            toUpdate.setRule(rule);
            toUpdate.setTech(tech);
            toUpdate.setEvaluation(evaluation);
            toUpdate.setResult(result);
            toUpdate.setMoney(money);
            toUpdate.setOver(true);
            evaluationDao.save(toUpdate);
            return ResultMessage.Success;
        }
    }

    /**
     * 申请评估
     * @param patentID 专利号
     * @param url 申请评估文件的路径
     * @return ResultMessage
     */
    @Override
    public ResultMessage applyEvaluation(String patentID, String url) {
        String time=new SimpleDateFormat("yyMMdd HH:mm:ss").format(new Date());
        Evaluation newEvaluation=new Evaluation();
        newEvaluation.setTime(time);
        newEvaluation.setPatentID(patentID);
        newEvaluation.setSpecification(url);
        newEvaluation.setOver(false);
        evaluationDao.save(newEvaluation);
        loanAllBLService.changeStateByPatentID(patentID,Patent_loan_state.to_be_pay_value);
        return ResultMessage.Success;
    }

    /**
     * 获取申请评估的文件url
     * @param patentID 专利号
     * @return 专利持有人申请评估文件的url
     */
    @Override
    public String getEvaluationApplicationURL(String patentID) {
        ArrayList<Evaluation> evaluations=evaluationDao.findByPatentIDSortByTime(patentID);
        if(evaluations==null || evaluations.size()==0){
            return null;
        }else {
            return evaluations.get(0).getSpecification();
        }
    }

    /**
     * 获取该专利评估的信息
     * @param patentID 专利号
     * @return EvaluationVO
     */
    @Override
    public EvaluationVO getEvaluation(String patentID) {
        ArrayList<Evaluation> evaluations=evaluationDao.findByPatentIDSortByTime(patentID);
        if(evaluations==null || evaluations.size()==0){
            return null;
        }else {
            Evaluation evaluation=evaluations.get(0);
            EvaluationVO vo=new EvaluationVO();
            vo.setPatentID(patentID);
            try {
                vo.setPatent(patentHelper.receivePatentName(patentID));
            } catch (IDNotExistsException e) {
                e.printStackTrace();
            }
            vo.setRule(evaluation.getRule());
            vo.setTech(evaluation.getTech());
            vo.setResult(evaluation.getResult());
            vo.setUrl(evaluation.getReport());
            vo.setEvaluation(evaluation.getEvaluation());
            vo.setMoney(evaluation.getMoney());
            return vo;
        }
    }

    /**
     * 判断该专利是否已经拥有评估结果
     * @param patentID 专利号
     * @return boolean
     */
    @Override
    public boolean ifValue(String patentID) {
        ArrayList<Evaluation> evaluations=evaluationDao.findByPatentIDSortByTime(patentID);
        if(evaluations==null || evaluations.size()==0){
            return false;
        }else {
            for (Evaluation evaluation : evaluations) {
                if (evaluation.isOver()) {
                    return true;
                }
            }
            return false;
        }
    }
}
