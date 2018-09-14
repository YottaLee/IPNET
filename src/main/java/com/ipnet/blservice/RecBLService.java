package com.ipnet.blservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.recvo.ManagerRecVO;
import com.ipnet.vo.recvo.PatentRecVO;
import com.ipnet.vo.recvo.RequireRecVO;

import java.util.ArrayList;

public interface RecBLService {

    ResultMessage buyPatentRec(String patent_id,int points);

    ResultMessage buyRequireRec(String person_id, String require,String detail,int points);

    ResultMessage buyManagerRec(String manager_id,int points);

    ArrayList<PatentRecVO> getPatentRecList();

    ArrayList<RequireRecVO> getRequireRecList();

    ArrayList<ManagerRecVO> getManagerRecList();
}
