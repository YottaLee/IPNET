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

    /**
     * 专利退池
     * @param ipId
     * @param ipSetId
     * @throws IDNotExistsException
     */
    @RequestMapping("/exitIpSet")
    public @ResponseBody
    void exitIpSet(@RequestParam String ipId, @RequestParam String ipSetId) throws IDNotExistsException {
        service.exitIpSet(ipId, ipSetId);
    }

    @RequestMapping("/updateIp")
    public @ResponseBody
    boolean updateIp(@RequestBody PatentVO ipVo) {
        return service.updateIp(ipVo);
    }

    /**
     * 专利拒绝入池
     * @param patentId
     * @param patentPoolId
     * @throws IDNotExistsException
     */
    @RequestMapping("/denyInvitationFromPool")
    public @ResponseBody void denyInvitationFromPool(@RequestParam String patentId , @RequestParam String patentPoolId) throws IDNotExistsException{
         service.denyInvitationFromPool(patentId , patentPoolId);
    }

    /**
     * 专利池邀请专利入池
     * @param patentId
     * @param patentPoolId
     * @throws IDNotExistsException
     */
    @RequestMapping("/sendInvitationFromPool")
    public void sendInvitationFromPool(String patentId, String patentPoolId) throws IDNotExistsException{
         service.sendInvitationFromPool(patentId , patentPoolId);
    }

    /**
     * 专利同意入池
     * @param patentId
     * @param patentPoolId
     * @return
     * @throws IDNotExistsException
     */
    @RequestMapping("/acceptInvitationFromPool")
    boolean acceptInvitationFromPool(String patentId , String patentPoolId) throws IDNotExistsException{
          return service.acceptInvitationFromPool(patentId , patentPoolId);
    }

    /**
     * 获取专利列表
     * @param userId 用户Id
     * @return 专利列表
     */
    @RequestMapping("/getPatentList")
    @ResponseBody
    public List<PatentVO> getPatentList(@RequestParam String userId) {
        return service.getPatentList(userId);
    }

    /**
     *
     *
     */
    @RequestMapping("/searchRelatedPatents")
    @ResponseBody
    public List<PatentVO> searchRelatedPatents(){
        return service.searchRelatedPatents();
    }

}
