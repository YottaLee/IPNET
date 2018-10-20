package com.ipnet.controller;

/**
 * @author gy
 */

import com.ipnet.blservice.PatentPoolBLService;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.vo.PatentPoolVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/PatentPool")
public class PatentPoolController {
    @Autowired
    private PatentPoolBLService service;

    @RequestMapping("/createPatentPool")
    public @ResponseBody  PatentPoolVO createPatentPool(@RequestParam String poolName, @RequestParam String holderId , @RequestParam String region ,@RequestParam String profile ,@RequestParam String date){
        System.out.println("1");
        return service.createPatentPool(poolName , holderId , region , profile , date);
    }

    @RequestMapping("/searchPatentPoolByID")
    public @ResponseBody PatentPoolVO searchPatentPoolByID(@RequestParam String patentPoolID){
        return service.searchPatentPoolByID(patentPoolID);
    }

    @RequestMapping("/searchPatentPoolByName")
    public @ResponseBody
    List<PatentPoolVO> searchPatentPoolByName(@RequestParam String patentPoolName){
        return service.searchPatentPoolByName(patentPoolName);
    }

    @RequestMapping("/deletePatentPool")
    public @ResponseBody Boolean deletePatentPool(@RequestParam String patentPoolID){
        return service.deletePatentPool(patentPoolID);
    }
//
//    @RequestMapping("/addPatentIntoPool")
//    public @ResponseBody Boolean addPatentIntoPool(@RequestParam String poolID,@RequestParam String PatentID) throws IDNotExistsException{
//        return  service.addPatentIntoPool(poolID,PatentID);
//    }

    @RequestMapping("/updateIpSet")
    public @ResponseBody boolean updateIpSet(@RequestBody PatentPoolVO ipSetVo){
        return service.updateIpSet(ipSetVo);
    }

    @RequestMapping("/applyIpSet")
    public @ResponseBody boolean applyIpSet(@RequestParam String ipId,@RequestParam String ipSetId) throws IDNotExistsException{
        return  service.applyIpSet(ipId , ipSetId);
    }

    @RequestMapping("/acceptIpApply")
    public @ResponseBody void acceptIpApply(@RequestParam String ipId , @RequestParam String ipSetId) throws  IDNotExistsException{
        service.acceptIpApply(ipId , ipSetId);
    }

    @RequestMapping("/denyIpApply")
    public @ResponseBody void denyIpApply(@RequestParam String ipId , @RequestParam String ipSetId) throws IDNotExistsException{
        service.denyIpApply(ipId , ipSetId);
    }

    @RequestMapping("/isFull")
    public @ResponseBody boolean isFull(@RequestParam String ipSetId) throws IDNotExistsException{
        return service.isFull(ipSetId);
    }

    /**
     * 获取专利池列表
     * @param userId 用户ID
     * @return 专利池列表
     * @throws IDNotExistsException
     */
    @RequestMapping("/getIPSETList")

    public @ResponseBody List<PatentPoolVO> getIPSETList(@RequestParam String userId) throws IDNotExistsException{
        return service.getIPSETList(userId);
    }

    @RequestMapping("/getNotFullPools")
    public  @ResponseBody List<PatentPoolVO> getNotFullPools() throws IDNotExistsException{
        return service.getNotFullPools();
    }
}
