package com.ipnet.bl;

import com.ipnet.blservice.RecBLService;
import com.ipnet.dao.PatentOrManagerRecDao;
import com.ipnet.dao.RequireRecDao;
import com.ipnet.entity.Rec.PatentOrManagerRec;
import com.ipnet.entity.Rec.RequireRec;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.recvo.ManagerRecVO;
import com.ipnet.vo.recvo.PatentRecVO;
import com.ipnet.vo.recvo.RequireRecVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecBL implements RecBLService {

    @Autowired
    private PatentOrManagerRecDao patentOrManagerRecDao;

    @Autowired
    private RequireRecDao requireRecDao;

    private String createID(String id){
        SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd-HHmmss");
        String rec_id=df.format(new Date())+id;
        return rec_id;
    }

    @Override
    public ResultMessage buyPatentRec(String patent_id, int points) {
        String rec_id=this.createID(patent_id);
        PatentOrManagerRec patentOrManagerRec=new PatentOrManagerRec(rec_id,patent_id,new Date(),points,0);
        patentOrManagerRecDao.save(patentOrManagerRec);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage buyRequireRec(String person, String require,String detail,int points) {
        String rec_id=this.createID(person);
        RequireRec requireRec=new RequireRec(rec_id,person,require,new Date(),points,detail);
        requireRecDao.save(requireRec);
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage buyManagerRec(String manager, int points) {
        String rec_id=this.createID(manager);
        PatentOrManagerRec patentOrManagerRec=new PatentOrManagerRec(rec_id,manager,new Date(),points,1);
        patentOrManagerRecDao.save(patentOrManagerRec);
        return ResultMessage.Success;
    }

    @Override
    public ArrayList<PatentRecVO> getPatentRecList() throws IDNotExistsException {
        List<PatentOrManagerRec> patentOrManagerRecs=patentOrManagerRecDao.findPatentOrManagerRecsByType(0);
        ArrayList<PatentRecVO> patentRecVOS=new ArrayList<>();
        for(PatentOrManagerRec patentOrManagerRec:patentOrManagerRecs){
            patentRecVOS.add(new PatentRecVO(patentOrManagerRec));
        }
        return patentRecVOS;

    }

    @Override
    public ArrayList<RequireRecVO> getRequireRecList() {
        List<RequireRec> requireRecs=  requireRecDao.findAll();
        ArrayList<RequireRecVO> requireRecVOS=new ArrayList<>();
        for(RequireRec requireRec:requireRecs){
            requireRecVOS.add(new RequireRecVO(requireRec));
        }
        return requireRecVOS;
    }

    @Override
    public ArrayList<ManagerRecVO> getManagerRecList() throws IDNotExistsException {
        List<PatentOrManagerRec> patentOrManagerRecs=patentOrManagerRecDao.findPatentOrManagerRecsByType(1);
        ArrayList<ManagerRecVO> managerRecVOS=new ArrayList<>();
        for(PatentOrManagerRec patentOrManagerRec:patentOrManagerRecs){
            managerRecVOS.add(new ManagerRecVO(patentOrManagerRec));
        }
        return managerRecVOS;
    }
}
