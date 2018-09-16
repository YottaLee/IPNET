package com.ipnet.blservice;

import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.PatentPoolVO;

import java.util.List;

/**
 * @author lzb
 * @date 2018/7/21 10:26
 */
public interface PatentPoolBLService {

    PatentPoolVO createPatentPool(String poolName,  String holderId ,  String region , String profile ,String date);

    PatentPoolVO searchPatentPoolByID(String patentPoolID);

    List<PatentPoolVO> searchPatentPoolByName(String patentPoolName);

    Boolean deletePatentPool(String patentPoolID);

    boolean applyIpSet(String ipId,String ipSetId) throws IDNotExistsException;   //专利申请入池

    void acceptIpApply(String ipId , String ipSetId) throws  IDNotExistsException;     //同意专利入池

    void denyIpApply(String ipId , String ipSetId) throws IDNotExistsException; //拒绝专利入池

    boolean isFull(String ipSetId) throws IDNotExistsException;        //池子是否已满

    boolean updateIpSet(PatentPoolVO ipSetVo);         //更新专利池

    List<PatentPoolVO> getIPSETList(String userId) throws IDNotExistsException;

    List<PatentPoolVO> getNotFullPools() throws IDNotExistsException;

}
