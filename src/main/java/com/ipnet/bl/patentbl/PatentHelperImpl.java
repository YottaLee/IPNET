package com.ipnet.bl.patentbl;

import com.ipnet.dao.PatentDao;
import com.ipnet.entity.Patent;
import com.ipnet.utility.IDNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatentHelperImpl implements PatentHelper{
    @Autowired
    private PatentDao patentDao;

    @Override
    public String receivePatentName(String patentId) throws IDNotExistsException {
        Optional<Patent> optionalPatent = this.patentDao.findById(patentId);
        if(optionalPatent.isPresent()==false || optionalPatent == null){
            throw new IDNotExistsException("patent id not exists");
        }
        Patent patent = optionalPatent.get();
        return patent.getPatent_name();
    }

    @Override
    public String receivePatentURL(String patentId) throws IDNotExistsException {
        Optional<Patent> optionalPatent = this.patentDao.findById(patentId);
        if(optionalPatent.isPresent()==false || optionalPatent == null){
            throw new IDNotExistsException("patent id not exists");
        }
        Patent patent = optionalPatent.get();
        return patent.getUrl();
    }

    @Override
    public String receivePatentProfile(String patentId) throws IDNotExistsException {
        Optional<Patent> optionalPatent = this.patentDao.findById(patentId);
        if(optionalPatent.isPresent()==false || optionalPatent == null){
            throw new IDNotExistsException("patent id not exists");
        }
        Patent patent = optionalPatent.get();
        return patent.getProfile();
    }

    @Override
    public String receivePatentType(String patentId) throws IDNotExistsException {
        Optional<Patent> optionalPatent = this.patentDao.findById(patentId);
        if(optionalPatent.isPresent()==false || optionalPatent == null){
            throw new IDNotExistsException("patent id not exists");
        }
        Patent patent = optionalPatent.get();
        return patent.getPatent_type();
    }
}
