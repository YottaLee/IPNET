package com.ipnet.bl.patentpoolbl;

import com.ipnet.vo.PatentPoolVO;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.bl.patentbl.PatentBLHelper;
import com.ipnet.blservice.PatentPoolBLService;
import com.ipnet.dao.PatentPoolDao;
import com.ipnet.entity.PatentPool;
import com.ipnet.utility.TransHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private PatentBLHelper patentBLHelper;


    @Override
    public PatentPoolVO createPatentPool(PatentPoolVO newPatentPool) {
        PatentPool domain = (PatentPool) this.transHelper.transTO(newPatentPool , PatentPool.class);
        PatentPool resultDomain = this.patentPoolDao.saveAndFlush(domain);
        PatentPoolVO resultVO = (PatentPoolVO) this.transHelper.transTO(resultDomain , PatentPoolVO.class);
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

    @Override
    public Boolean addPatentIntoPool(String poolID, String patentID) throws IDNotExistsException {
        Optional<PatentPool> optionalPatentPool = this.patentPoolDao.findById(poolID);
        if (optionalPatentPool == null || optionalPatentPool.isPresent() == false) {
            throw new IDNotExistsException("pool id not exists");
        }
        PatentPool pool = optionalPatentPool.get();

        if (this.patentBLHelper.ifPatentExists(patentID) == false){
            throw new IDNotExistsException("patent id not exists");
        }

        List<String> patentIDList = pool.getPatents();
        patentIDList.add(patentID);

        this.patentPoolDao.saveAndFlush(pool);

        return true;
    }
}
