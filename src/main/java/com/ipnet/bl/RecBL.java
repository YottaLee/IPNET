package com.ipnet.bl;

import com.ipnet.blservice.RecBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.vo.recvo.ManagerRecVO;
import com.ipnet.vo.recvo.PatentRecVO;
import com.ipnet.vo.recvo.RequireRecVO;

import java.util.ArrayList;

public class RecBL implements RecBLService {
    @Override
    public ResultMessage buyPatentRec(String patent_id, int points) {
        return null;
    }

    @Override
    public ResultMessage buyRequireRec(String patent_id, int points) {
        return null;
    }

    @Override
    public ResultMessage buyManagerRec(String patent_id, int points) {
        return null;
    }

    @Override
    public ArrayList<PatentRecVO> getPatentRecList() {
        return null;
    }

    @Override
    public ArrayList<RequireRecVO> getRequireRecList() {
        return null;
    }

    @Override
    public ArrayList<ManagerRecVO> getManagerRecList() {
        return null;
    }
}
