package com.ipnet.controller;

/**
 * @author gy
 */

import com.ipnet.blservice.PatentBLService;
import com.ipnet.enums.Patent_state;
import com.ipnet.enums.ResultMessage;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.PatentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Patent")
public class PatentController {
    @Autowired
    private PatentBLService service;

    @RequestMapping("/createPatent")
    public @ResponseBody
    PatentVO createPatent(@RequestBody PatentVO newPatent) {
        return service.createPatent(newPatent);
    }

    @RequestMapping("/searchPatentByID")
    public @ResponseBody
    PatentVO searchPatentByID(@RequestParam String patentID) {
        return service.searchPatentByID(patentID);
    }

    @RequestMapping("/searchPatentByName")
    public @ResponseBody
    List<PatentVO> searchPatentByName(@RequestParam String name) {
        return service.searchPatentByName(name);
    }

    @RequestMapping("/deletePatent")
    public @ResponseBody
    Boolean deletePatent(@RequestParam String patentID) {
        return service.deletePatent(patentID);
    }

    @RequestMapping("/updatePatentState")
    public @ResponseBody
    Boolean updatePatentState(@RequestParam Patent_state newState, @RequestParam String patentID) throws IDNotExistsException {
        return service.updatePatentState(newState, patentID);
    }

    @RequestMapping("/exitIpSet")
    public @ResponseBody
    void exitIpSet(@RequestParam String ipId, @RequestParam String ipSetId) throws IDNotExistsException {
        service.exitIpSet(ipId, ipSetId);
    }

    @RequestMapping("/searchIp")
    public @ResponseBody
    PatentVO searchIp(@RequestParam String info) {
        return service.searchIp(info);
    }

    @RequestMapping("/applyIpSet")
    public @ResponseBody
    boolean applyIpSet(@RequestParam String ipId, @RequestParam String ipSetId) throws IDNotExistsException {
        return service.applyIpSet(ipId, ipSetId);
    }

    @RequestMapping("/updateIp")
    public @ResponseBody
    boolean updateIp(@RequestBody PatentVO ipVo) {
        return service.updateIp(ipVo);
    }

    /**
     * 获取专利列表
     * @param userId 用户Id
     * @return 专利列表
     */
    @RequestMapping("/getPatentList")
    @ResponseBody
    public ArrayList<PatentVO> getPatentList(String userId) {
        return null;
    }

}
