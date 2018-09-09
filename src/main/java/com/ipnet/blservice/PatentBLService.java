package com.ipnet.blservice;

import com.ipnet.enums.Patent_state;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.PatentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzb
 * @date 2018/7/21 10:12
 */
public interface PatentBLService {

    PatentVO createPatent(PatentVO newPatent);

    PatentVO searchPatentByID(String patentID);

    List<PatentVO> searchPatentByName(String name);

    Boolean deletePatent(String patentID);

    Boolean updatePatentState(Patent_state newState, String patentID) throws IDNotExistsException;

    void exitIpSet(String ipId,String ipSetId) throws IDNotExistsException;     //专利退池

    PatentVO searchIp(String info);     //专利详情

    boolean applyIpSet(String ipId,String ipSetId) throws IDNotExistsException;   //专利申请入池

    boolean updateIp(PatentVO ipVo);  //更新专利

    void denyInvitationFromPool(String patentId , String patentPoolId) throws IDNotExistsException;

    void sendInvitationFromPool(String patentId , String patentPoolId) throws IDNotExistsException;

    List<PatentVO> getPatentList(String userId);

}
