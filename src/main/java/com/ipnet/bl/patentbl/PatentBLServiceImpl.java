package com.ipnet.bl.patentbl;

import com.ipnet.dao.PatentPoolDao;
import com.ipnet.entity.PatentPool;
import com.ipnet.vo.PatentVO;
import com.ipnet.blservice.PatentBLService;
import com.ipnet.dao.PatentDao;
import com.ipnet.entity.Patent;
import com.ipnet.enums.Patent_state;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.utility.TransHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lzb
 * @date 2018/7/21 10:35
 */
@Service
public class PatentBLServiceImpl implements PatentBLService {

    @Autowired
    private PatentDao patentDao;

    @Autowired
    private PatentPoolDao patentpoolDao;

    @Autowired
    private TransHelper transHelper;

    @Override
    public PatentVO createPatent(PatentVO newPatent) {
        Patent patent = (Patent) this.transHelper.transTO(newPatent , Patent.class);
        Patent realPatent = this.patentDao.saveAndFlush(patent);
        PatentVO resultVO = (PatentVO) this.transHelper.transTO(realPatent , PatentVO.class);
        return resultVO;
    }

    @Override
    public PatentVO searchPatentByID(String patentID) {
        Optional<Patent> optionalPatent = this.patentDao.findById(patentID);
        if (optionalPatent.isPresent() ==false){
            return null;
        }
        PatentVO resultVO = (PatentVO) this.transHelper.transTO(optionalPatent.get() , PatentVO.class);
        return resultVO;
    }



    @Override
    public List<PatentVO> searchPatentByName(String name) {
        List<Patent> patentList = this.patentDao.searchPatentByPatentName(name);
        if (patentList == null || patentList.size()==0){
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public Boolean deletePatent(String patentID) {
        this.patentDao.deleteById(patentID);
        return true;
    }

    @Override
    public Boolean updatePatentState(Patent_state newState, String patentID) throws IDNotExistsException {
        Optional<Patent> optionalPatent = this.patentDao.findById(patentID);
        if (optionalPatent.isPresent() == false){
            throw new IDNotExistsException("patent id not exists");
        }
        Patent patent = optionalPatent.get();
        patent.setState(newState);
        Patent resultPatent = this.patentDao.saveAndFlush(patent);
        return true;
    }

    @Override
    public void exitIpSet(String ipId,String ipSetId) throws IDNotExistsException {
            Optional<PatentPool> option = this.patentpoolDao.findById(ipSetId);
            if(option!=null || option.isPresent() == false){
                 throw new IDNotExistsException("pool id not exists");
            }
            PatentPool pool = (PatentPool) option.get();
            List<String> patentlist=pool.getPatents();
            for(String str : patentlist){
                if(ipId.equals(str)){
                    patentlist.remove(str);
                }
            }
            pool.setPatents(patentlist);
            this.patentpoolDao.saveAndFlush(pool);

            //设置ip的状态
    }


    @Override
    public boolean updateIp(PatentVO ipVo){
        boolean flag = false;
        String ipId = ipVo.getPatent_id();
        if(this.patentDao.existsById(ipId)){
            flag = true;
            Patent patent = (Patent) this.transHelper.transTO(ipVo , Patent.class);
            this.patentDao.deleteById(ipId);
            this.patentDao.saveAndFlush(patent);
        }

        return flag;
    }
    @Override
    public boolean acceptInvitationFromPool(String patentId , String patentPoolId) throws IDNotExistsException{
        boolean flag = false;
        if (!this.patentDao.existsById(patentId)){
            throw new IDNotExistsException("patent id not exists");
        }

        Patent patent = this.getPatentById(patentId);
        PatentPool pool = this.patentpoolDao.findById(patentPoolId).get();
        if(!pool.isFull()){
            flag = true;
            patent.deleteInvitationFromPool(patentPoolId);
            pool.addPatent(patentId);
        }
        return flag;
    }
    @Override
    public void denyInvitationFromPool(String patentId, String patentPoolId) throws IDNotExistsException {
        if (!this.patentDao.existsById(patentId)){
            throw new IDNotExistsException("patent id not exists");
        }

        Patent patent = this.getPatentById(patentId);
        patent.deleteInvitationFromPool(patentPoolId);
        this.savePatent(patent);

    }

    @Override
    public void sendInvitationFromPool(String patentId, String patentPoolId) throws IDNotExistsException {
        if (!this.patentDao.existsById(patentId)){
            throw new IDNotExistsException("patent id not exists");
        }
        Patent patent = this.getPatentById(patentId);
        patent.addInvitationFromPool(patentPoolId);
        this.savePatent(patent);
    }

    @Override
    public List<PatentVO> getPatentList(String userId){
         List<Patent> patentList = this.patentDao.searchPatentByHolder(userId);
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }
    private Patent getPatentById(String patentId) throws IDNotExistsException{
        Optional<Patent> optionalPatent = this.patentDao.findById(patentId);
        if (optionalPatent.isPresent() ==false){
            throw new IDNotExistsException("patent id not exists");
        }

        return optionalPatent.get();
    }


    private void savePatent(Patent patent){
        this.patentDao.saveAndFlush(patent);
    }


}
