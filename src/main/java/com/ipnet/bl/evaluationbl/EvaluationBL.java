package com.ipnet.bl.evaluationbl;

import com.ipnet.blservice.EvaluationBLService;
import com.ipnet.dao.EvaluationDao;
import com.ipnet.entity.Evaluation;
import com.ipnet.enums.ResultMessage;
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

    @Override
    public ResultMessage submitReport(String patentID, String url, int evaluation) {
        ArrayList<Evaluation> evaluations=evaluationDao.findByPatentIDSortByTime(patentID);
        if(evaluations==null||evaluations.size()==0){
           return ResultMessage.Fail;
        }else {
            Evaluation toUpdate=evaluations.get(0);
            toUpdate.setReport(url);
            toUpdate.setMoney(evaluation);
            toUpdate.setOver(true);
            evaluationDao.save(toUpdate);
            return ResultMessage.Success;
        }
    }

    @Override
    public ResultMessage applyEvaluation(String patentID, String url) {
        String time=new SimpleDateFormat("yyMMdd HH:mm:ss").format(new Date());
        Evaluation newEvaluation=new Evaluation(0,time,null,patentID,url,null,0.0,null,false);
        evaluationDao.save(newEvaluation);
        return ResultMessage.Success;
    }

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

    @Override
    public EvaluationVO getEvaluation(String patentID) {
        ArrayList<Evaluation> evaluations=evaluationDao.findByPatentIDSortByTime(patentID);
        if(evaluations==null || evaluations.size()==0){
            return null;
        }else {
            for (Evaluation evaluation : evaluations) {
                if (evaluation.isOver()) {
                    EvaluationVO vo=new EvaluationVO();
                    vo.setPatentID(patentID);
                    vo.setUrl(evaluation.getReport());
                    vo.setEvaluation(evaluation.getMoney());
                    return vo;
                }
            }
            return null;
        }
    }
}
