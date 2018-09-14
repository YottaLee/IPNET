package com.ipnet.blservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.recvo.ManagerRecVO;
import com.ipnet.vo.recvo.PatentRecVO;
import com.ipnet.vo.recvo.RequireRecVO;

import java.util.ArrayList;

public interface RecBLService {

    ResultMessage buyPatentRec(String patent_id,int points);

    ResultMessage buyRequireRec(String patent_id,int points);

    ResultMessage buyManagerRec(String patent_id,int points);

    ArrayList<PatentRecVO> getPatentRecList();

    ArrayList<RequireRecVO> getRequireRecList();

    ArrayList<ManagerRecVO> getManagerRecList();
}
