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

import java.util.List;

@Controller
@RequestMapping("/PatentPool")
public class PatentPoolController {
     @Autowired
     private PatentPoolBLService service;

     @RequestMapping("/createPatentPool")
     public @ResponseBody  PatentPoolVO createPatentPool(@RequestBody PatentPoolVO newPatentPool){
          return service.createPatentPool(newPatentPool);
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

    @RequestMapping("/addPatentIntoPool")
    public @ResponseBody Boolean addPatentIntoPool(@RequestParam String poolID,@RequestParam String PatentID) throws IDNotExistsException{
         return  service.addPatentIntoPool(poolID,PatentID);
    }

    @RequestMapping("/updateIpSet")
    public @ResponseBody boolean updateIpSet(@RequestBody PatentPoolVO ipSetVo){
         return service.updateIpSet(ipSetVo);
    }

    /**
     * 专利申请入池
     * @param ipId
     * @param ipSetId
     * @return
     * @throws IDNotExistsException
     */
    @RequestMapping("/applyIpSet")
    public @ResponseBody boolean applyIpSet(@RequestParam String ipId,@RequestParam String ipSetId) throws IDNotExistsException{
         return  service.applyIpSet(ipId , ipSetId);
    }

    /**
     * 同意专利入池
     * @param ipId
     * @param ipSetId
     * @throws IDNotExistsException
     */
    @RequestMapping("/acceptIpApply")
    public @ResponseBody void acceptIpApply(@RequestParam String ipId , @RequestParam String ipSetId) throws  IDNotExistsException{
         service.acceptIpApply(ipId , ipSetId);
    }

    /**
     * 拒绝专利入池
     * @param ipId
     * @param ipSetId
     * @throws IDNotExistsException
     */
    @RequestMapping("/denyIpApply")
    public @ResponseBody void denyIpApply(@RequestParam String ipId , @RequestParam String ipSetId) throws IDNotExistsException{
         service.denyIpApply(ipId , ipSetId);
    }

    /**
     * 专利池是否已满
     * @param ipSetId
     * @return
     * @throws IDNotExistsException
     */
    @RequestMapping("/isFull")
    public @ResponseBody boolean isFull(@RequestParam String ipSetId) throws IDNotExistsException{
         return service.isFull(ipSetId);
    }
}
