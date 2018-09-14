package com.ipnet.bl.patentpoolbl;

import com.ipnet.dao.PatentPoolDao;
import com.ipnet.entity.PatentPool;
import com.ipnet.utility.IDNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PoolHelperImpl implements PoolHelper {
    @Autowired
    private PatentPoolDao patentPoolDao;
    @Override
    public String receivePoolName(String poolId) throws IDNotExistsException {
        Optional<PatentPool> optionalPool = this.patentPoolDao.findById(poolId);
        if(optionalPool.isPresent()==false || optionalPool == null){
            throw new IDNotExistsException("pool id not exists");
        }
        PatentPool pool = optionalPool.get();
        return pool.getName();
    }

    @Override
    public String receivePoolProfile(String poolId) throws IDNotExistsException {
        Optional<PatentPool> optionalPool = this.patentPoolDao.findById(poolId);
        if(optionalPool.isPresent()==false || optionalPool == null){
            throw new IDNotExistsException("pool id not exists");
        }
        PatentPool pool = optionalPool.get();
        return pool.getProfile();
    }

    @Override
    public String receivePoolURL(String poolId) throws IDNotExistsException {
        Optional<PatentPool> optionalPool = this.patentPoolDao.findById(poolId);
        if(optionalPool.isPresent()==false || optionalPool == null){
            throw new IDNotExistsException("pool id not exists");
        }
        PatentPool pool = optionalPool.get();
        return pool.getPicture();
    }

    @Override
    public int receivePoolPatentsNum(String poolId) throws IDNotExistsException {
        Optional<PatentPool> optionalPool = this.patentPoolDao.findById(poolId);
        if(optionalPool.isPresent()==false || optionalPool == null){
            throw new IDNotExistsException("pool id not exists");
        }
        PatentPool pool = optionalPool.get();
        if(pool.getPatents() == null){
            return 0;
        }
        return pool.getPatents().size();
    }
}
