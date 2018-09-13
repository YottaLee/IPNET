package com.ipnet.blservice;

import com.ipnet.enums.Patent_state;
import com.ipnet.enums.Region;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.PatentVO;
import org.springframework.web.bind.annotation.RequestBody;

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

    List<PatentVO> searchPatent(String info);

    Boolean deletePatent(String patentID);

    Boolean updatePatentState(Patent_state newState, String patentID) throws IDNotExistsException;

    void exitIpSet(String ipId,String ipSetId) throws IDNotExistsException;     //专利退池

    boolean updateIp(PatentVO ipVo);  //更新专利

    void denyInvitationFromPool(String patentId , String patentPoolId) throws IDNotExistsException;

    boolean acceptInvitationFromPool(String patentId , String patentPoolId) throws IDNotExistsException;

    void sendInvitationFromPool(String patentId , String patentPoolId) throws IDNotExistsException;

     List<PatentVO> searchRelatedPatents();

    List<PatentVO> searchPatentByRegion(Region region);

    List<PatentVO> getPatentList(String userId);

}
