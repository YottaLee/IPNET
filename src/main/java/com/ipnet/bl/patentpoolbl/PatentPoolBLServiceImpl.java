package com.ipnet.bl.patentpoolbl;

import com.ipnet.bl.patentbl.Invitation;
import com.ipnet.dao.PatentDao;
import com.ipnet.entity.Patent;
import com.ipnet.enums.Industry;
import com.ipnet.vo.PatentPoolVO;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.bl.patentbl.PatentBLHelper;
import com.ipnet.blservice.PatentPoolBLService;
import com.ipnet.dao.PatentPoolDao;
import com.ipnet.entity.PatentPool;
import com.ipnet.utility.TransHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lzb
 * @date 2018/7/21 10:36
 */
@Service
public class PatentPoolBLServiceImpl implements PatentPoolBLService {

    @Autowired
    private TransHelper transHelper;

    @Autowired
    private PatentPoolDao patentPoolDao;

    @Autowired
    private  PatentDao patentDao;

    @Autowired
    private PatentBLHelper patentBLHelper;


    @Override
    public PatentPoolVO createPatentPool(String poolName,  String holderId ,  String region , String profile ,String date) {
        PatentPool pool =  new PatentPool();
        pool.setName(poolName);
        pool.setAmount(20);
        pool.setPatents(new ArrayList<String>());
        pool.setApplypatents(new ArrayList<String>());
        pool.setDescription(profile);
        pool.setIndustry(Industry.Holder);
        pool.setManagers(new ArrayList<String>());
        pool.setPicture("  ");
        pool.setOwner(holderId);
        pool.setUsers(new ArrayList<String>());
        pool.setProfile(profile);
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pool.setCreateTime(form.format(new Date()));
        List<PatentPool> tempList = this.patentPoolDao.findAll();
        pool.setId(holderId+"_"+tempList.size());
        PatentPool resultDomain = this.patentPoolDao.saveAndFlush(pool);
        PatentPoolVO resultVO = (PatentPoolVO) this.transHelper.transTO(resultDomain , PatentPoolVO.class);
        List<PatentPool> newList = this.patentPoolDao.searchPatentPoolByName(poolName);
        for(int i =1 ; i< newList.size() ; i ++) {
            deletePatentPool(newList.get(i).getId());
        }
        //System.out.println("已调用");
        return resultVO;
    }

    @Override
    public PatentPoolVO searchPatentPoolByID(String patentPoolID) {
        Optional<PatentPool> optionalPatentPool = this.patentPoolDao.findById(patentPoolID);
        if (optionalPatentPool == null || optionalPatentPool.isPresent() == false) {
            return null;
        }
        PatentPool pool = optionalPatentPool.get();
        PatentPoolVO resultVO = (PatentPoolVO) this.transHelper.transTO(pool , PatentPoolVO.class);

        return resultVO;
    }

    @Override
    public List<PatentPoolVO> searchPatentPoolByName(String patentPoolName) {
        List<PatentPool> poolList = this.patentPoolDao.searchPatentPoolByName(patentPoolName);
        if (poolList == null || poolList.size() == 0) {
            return null;
        }
        return poolList.stream()
                .filter(patentPool -> patentPool!=null)
                .map(patentPool -> (PatentPoolVO)this.transHelper.transTO(patentPool,PatentPoolVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deletePatentPool(String patentPoolID) {
        this.patentPoolDao.deleteById(patentPoolID);
        return true;
    }


    /**
     * @author gy
     * @param ipId
     * @param ipSetId
     * @expalnmation:该方法放在了patent里面，因为只改变patent的属性，会发生循环依赖
     */

    /**
     * @author gy
     * @param ipSetVo
     * @return
     */
    @Override
    public boolean updateIpSet(PatentPoolVO ipSetVo){
        boolean flag = false;
        PatentPool domain = (PatentPool) this.transHelper.transTO(ipSetVo, PatentPool.class);
        String patentPoolId = domain.getId();
        if(searchPatentPoolByID(patentPoolId)!=null) {
            this.patentPoolDao.deleteById(patentPoolId);
            this.patentPoolDao.saveAndFlush(domain);
            flag = true;
        }
        return flag;
    }


    /**
     * @author gy
     * @param ipSetId
     * @return
     * @throws IDNotExistsException
     */
   @Override
    public  boolean isFull(String ipSetId) throws IDNotExistsException {
        Optional<PatentPool> option = this.patentPoolDao.findById(ipSetId);
        if(option ==null || option.isPresent() == false){
            throw new IDNotExistsException("pool id not exists");
        }
        PatentPool pool = option.get();
        return pool.isFull();
    }

    /**
     * @author gy
     * @param ipId
     * @param ipSetId
     * @return
     * @throws IDNotExistsException
     */
    @Override
    public boolean applyIpSet(String ipId,String ipSetId) throws IDNotExistsException{
        boolean flag = false;
        Optional<PatentPool> option = this.patentPoolDao.findById(ipSetId);
        if(option ==null || option.isPresent() == false){
            throw new IDNotExistsException("pool id not exists");
        }
        else {
            PatentPool pool = option.get();
            pool.addToapplyPatents(ipId);
            this.patentPoolDao.saveAndFlush(pool);
            flag = true;
        }
        return flag;
    }

    /**
     * @author gy
     * @param ipId
     * @param ipSetId
     * @throws IDNotExistsException
     */
    @Override
    public void acceptIpApply(String ipId , String ipSetId) throws  IDNotExistsException{
        Optional<PatentPool> option = this.patentPoolDao.findById(ipSetId);
        if(option ==null || option.isPresent() == false){
            throw new IDNotExistsException("pool id not exists");
        }
        if (this.patentBLHelper.ifPatentExists(ipId) == false){
            throw new IDNotExistsException("patent id not exists");
        }
        PatentPool pool = option.get();
        pool.acceptApply(ipId);
        this.patentPoolDao.saveAndFlush(pool);

        //个人感觉需要加一个ip的所属专利池的属性
        Patent patent = this.patentDao.findById(ipId).get();
        patent.setPool_id(ipSetId);
        this.patentDao.saveAndFlush(patent);
    }

    @Override
    public void denyIpApply(String ipId , String ipSetId) throws IDNotExistsException{
        if (!this.patentPoolDao.existsById(ipSetId)){
            throw new IDNotExistsException("pool id not exists");
        }
        PatentPool pool = this.patentPoolDao.findById(ipSetId).get();
        pool.denyApply(ipId);
        this.patentPoolDao.saveAndFlush(pool);
    }

    @Override
    public List<PatentPoolVO> getIPSETList(String userId) throws IDNotExistsException{
        List<PatentPool> poolists = this.patentPoolDao.searchPatentPoolByOwner(userId);
        if (poolists == null || poolists.size() == 0) {
            return null;
        }
        return poolists.stream()
                .filter(patentPool -> patentPool!=null)
                .map(patentPool -> (PatentPoolVO)this.transHelper.transTO(patentPool,PatentPoolVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PatentPoolVO> getNotFullPools() throws IDNotExistsException {
        List<PatentPool> poolists = this.patentPoolDao.findAll();
        for(PatentPool pool : poolists){
            if(isFull(pool.getId())){
                poolists.remove(pool);
            }
        }
        if (poolists.size() ==0 || poolists==null){
            return null;
        }
        return poolists.stream()
                .filter(patentPool -> patentPool!=null)
                .map(patentPool -> (PatentPoolVO)this.transHelper.transTO(patentPool,PatentPoolVO.class))
                .collect(Collectors.toList());
    }

}
