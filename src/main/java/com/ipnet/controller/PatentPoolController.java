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

    @RequestMapping("/inviteIpSet")
    public @ResponseBody void inviteIpSet(@RequestParam String ipId,@RequestParam String ipSetId){
          service.inviteIpSet(ipId,ipSetId);
    }

    @RequestMapping("/updateIpSet")
    public @ResponseBody boolean updateIpSet(@RequestBody PatentPoolVO ipSetVo){
         return service.updateIpSet(ipSetVo);
    }
}
