package com.ipnet.blservice;

import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.PatentPoolVO;

import java.util.List;

/**
 * @author lzb
 * @date 2018/7/21 10:26
 */
public interface PatentPoolBLService {

    PatentPoolVO createPatentPool(PatentPoolVO newPatentPool);

    PatentPoolVO searchPatentPoolByID(String patentPoolID);

    List<PatentPoolVO> searchPatentPoolByName(String patentPoolName);

    Boolean deletePatentPool(String patentPoolID);

    Boolean addPatentIntoPool(String poolID, String PatentID) throws IDNotExistsException;



}
