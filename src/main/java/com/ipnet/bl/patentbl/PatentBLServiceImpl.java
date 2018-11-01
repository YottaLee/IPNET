package com.ipnet.bl.patentbl;

import com.ipnet.dao.InvitationDao;
import com.ipnet.dao.PatentPoolDao;
import com.ipnet.entity.PatentPool;
import com.ipnet.enums.ResultMessage;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ipnet.enums.Patent_state.free;

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
    private InvitationDao invitationDao;

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
    public ResultMessage entryPatent(String patentID, String patent, String userId, String holder, String url, String applyTime, String type, String district, String profile) {
        Patent p = new Patent();
        p.setPatent_id(patentID);
        p.setPool_id("");
        p.setUserId(userId);
        p.setPatent_name(patent);
        p.setPatent_holder(holder);
        p.setUrl(url);
        p.setApply_date(applyTime);
        p.setProfile(profile);
        p.setPatent_type(type);
        p.setRegion(district);
        p.setInvitationPoolIdList(new ArrayList<String>());
        p.setState(free);
        p.setValid_period("2");      //有效期限设定;    这个设值有待商榷
        this.patentDao.saveAndFlush(p);
        return null;
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
    public List<PatentVO> getPatentList(String userId){
        List<Patent> patentList = this.patentDao.searchPatentsByUserId(userId);
        if(patentList.size() == 0 ||patentList == null){
            System.out.println("list为空");
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<PatentVO> searchRelatedPatents(){
        List<Patent> patentList = this.patentDao.searchRelatedPatents("");
        if(patentList.size() == 0 ||patentList == null){
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<PatentVO> searchPatentByPool(String poolId) {
        List<Patent> patentList = this.patentDao.searchRelatedPatents(poolId);
        if(patentList.size() == 0 ||patentList == null){
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<PatentVO> searchPatentsByApplyDate(String StartDate, String endDate) {
        List<Patent> OriginList = this.patentDao.findAll();
        List<Patent> patentList = new ArrayList<Patent>();
        for(Patent p : OriginList) {
            if(StartDate.equals("")){
                if(Integer.parseInt(p.getApply_date())<= Integer.parseInt(endDate)){
                    patentList.add(p);
                }
            }
            else if(endDate.equals("")){
                if(Integer.parseInt(p.getApply_date()) >= Integer.parseInt(StartDate)) {
                    patentList.add(p);
                }
            }
            else{
                if(Integer.parseInt(p.getApply_date()) <=Integer.parseInt(endDate) && Integer.parseInt(p.getApply_date())>= Integer.parseInt(StartDate)) {
                    patentList.add(p);
                }
            }
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<PatentVO> searchPatentByRegion(String region){
        List<Patent> patentList = this.patentDao.searchPatentsByRegion(region);
        if(patentList.size() == 0 || patentList == null){
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }
    @Override
    public List<PatentVO> searchPatentsByState(Patent_state state){
        List<Patent> patentList = this.patentDao.searchPatentsByState(state);
        if(patentList.size() == 0 || patentList == null){
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<PatentVO> searchPatentsByType(String patent_type) {
        List<Patent> patentList = this.patentDao.searchPatentsByType(patent_type);
        if(patentList.size() == 0 || patentList == null){
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<PatentVO> searchPatentsByValid_period(String valid_period){
        List<Patent> patentList = this.patentDao.searchPatentsByValid_period(valid_period);
        if(patentList.size() == 0 || patentList == null){
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<PatentVO> searchPatent(String info) {
        List<PatentVO> voList = new ArrayList<PatentVO>();
        System.out.println(info);
        if(info.equals("")) {
            System.out.println("info为" +"空");
            System.out.println(info);
            List<Patent> patentList = this.patentDao.findAll();
            voList = patentList.stream()
                    .filter(patent -> patent!=null)
                    .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                    .collect(Collectors.toList());
        }
        else{
        List<Patent> patentList = this.patentDao.searchPatent(info);
        if(patentList.size() == 0 ||patentList == null){
            System.err.println("yes");
            return null;
        }
        voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        }
        System.err.println(voList.size());
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

            Optional<Patent> optionalPatent = this.patentDao.findById(ipId);
            if(option!=null || option.isPresent() == false){
                 throw new IDNotExistsException("patent id not exists");
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


             Patent realPatent = optionalPatent.get();
             realPatent.setPool_id("");
             this.patentDao.saveAndFlush(realPatent);
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
            //修改patent
            patent.deleteInvitationFromPool(patentPoolId);
            patent.setPool_id(patentPoolId);
            //修改pool
            pool.addPatent(patentId);
            this.patentpoolDao.saveAndFlush(pool);
            this.patentDao.saveAndFlush(patent);
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
        if(!this.patentpoolDao.existsById(patentPoolId)) {
            throw new IDNotExistsException("pool id not exists");
        }
        Patent patent = this.getPatentById(patentId);
        patent.addInvitationFromPool(patentPoolId);
        Invitation invitation = new Invitation();
        invitation.setPatentId(patentId);
        invitation.setPatentPoolId(patentPoolId);
        invitation.setDate(new Date());
        this.invitationDao.saveAndFlush(invitation);      //invitation的id在哪
        this.savePatent(patent);
    }

    @Override
    public List<PatentVO> recommendPatent() {

        List<Patent> patents = this.patentDao.findAll();
        int random = (int)(Math.random() * patents.size());
        List<Patent> patentList= new ArrayList<Patent>();
        if(patents.size() == 0 || patents == null){
            return null;
        }
        for(int i = 0 ; i <= random%patents.size(); i++){   //推荐的个数
             int randIndex = (int)(Math.random());
             patentList.add(patents.get(randIndex%patents.size()));
        }

        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return  voList;
    }

    @Override
    public List<Invitation> receiveInvitation(String holdId) {
         List<Patent> patentList = this.patentDao.searchPatentsByUserId(holdId);
         List<Invitation> invitationList = new ArrayList<Invitation>();
         for(Patent p : patentList) {
             for(Invitation i : this.invitationDao.searchInvitationByPatentId(p.getPatent_id())) {
                 invitationList.add(i);
             }

         }
         return invitationList;
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
