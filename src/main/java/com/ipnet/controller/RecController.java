package com.ipnet.controller;

import com.ipnet.blservice.RecBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.recvo.ManagerRecVO;
import com.ipnet.vo.recvo.PatentRecVO;
import com.ipnet.vo.recvo.RequireRecVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/rec")
public class RecController {

    @Autowired
    private RecBLService recBLService;

    @RequestMapping("/buyPatentRec")
    public @ResponseBody ResultMessage buyPatentRec(@RequestParam String patent_id, @RequestParam int points){
        return recBLService.buyPatentRec(patent_id,points);
    }

    @RequestMapping("/buyRequireRec")
    public @ResponseBody ResultMessage buyRequireRec(@RequestParam String person_id, @RequestParam String require,@RequestParam String detail,@RequestParam int points){
        return recBLService.buyRequireRec(person_id,require,detail,points);
    }

    @RequestMapping("/buyManagerRec")
    public @ResponseBody ResultMessage buyManagerRec(@RequestParam String manager_id,@RequestParam int points){
        return recBLService.buyManagerRec(manager_id,points);
    }

    @RequestMapping("/getPatentRecList")
    public @ResponseBody
    ArrayList<PatentRecVO> getPatentRecList() throws IDNotExistsException{
        return recBLService.getPatentRecList();
    }

    @RequestMapping("/getRequireRecList")
    public @ResponseBody ArrayList<RequireRecVO> getRequireRecList(){
        return recBLService.getRequireRecList();
    }

    @RequestMapping("getManagerRecList")
    public @ResponseBody  ArrayList<ManagerRecVO> getManagerRecList() throws IDNotExistsException{
        return recBLService.getManagerRecList();
    }
}
