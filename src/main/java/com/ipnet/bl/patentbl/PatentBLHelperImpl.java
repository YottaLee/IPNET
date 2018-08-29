package com.ipnet.bl.patentbl;

import com.ipnet.dao.PatentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lzb
 * @date 2018/7/22 23:05
 */
@Service
public class PatentBLHelperImpl implements PatentBLHelper {

    @Autowired
    private PatentDao patentDao;

    @Override
    public Boolean ifPatentExists(String patentID) {
        return this.patentDao.existsById(patentID);
    }
}
