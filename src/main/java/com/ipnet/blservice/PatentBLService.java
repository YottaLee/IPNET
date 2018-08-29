package com.ipnet.blservice;

import com.ipnet.enums.Patent_state;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.PatentVO;

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



}
