package com.ipnet.controller.communitycontroller;

import com.ipnet.blservice.communityservice.CommunityUserBLService;
import com.ipnet.entity.communityentity.Mine;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.CUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/C_User")
public class CommunityUserController {

    @Autowired
    private CommunityUserBLService blService;

    @RequestMapping("/info")
    public @ResponseBody
    CUserVO getUserInfo(String userID){
        return blService.getUserInfo(userID);
    }

    @RequestMapping("/signature")
    public @ResponseBody
    void modifySignature(String userID,String newSign){
        blService.modifySignature(userID,newSign);
    }

    @RequestMapping("/tag")
    public @ResponseBody
    void modifyTags(String userID, ArrayList<Post_tag> tags){
        blService.modifyTag(userID,tags);
    }

    @RequestMapping("/interestpost")
    public @ResponseBody
    void attentionPost(String userID,String postID){
        blService.interestPost(userID,postID);
    }

    @RequestMapping("/interestuser")
    public @ResponseBody
    void attentionUser(String starID,String fanID){
        blService.interestUser(starID,fanID);
    }

    @RequestMapping("/uninterestpost")
    public @ResponseBody
    void unInterestPost(String userID,String postID){
        blService.cancelInterest(userID,postID);
    }

    @RequestMapping("/uninterestuser")
    public @ResponseBody
    void unInterestUser(String starID,String fanID){
        blService.cancelInterestUser(starID,fanID);
    }


    @RequestMapping("/getRelease")
    public @ResponseBody
    List<Mine> getRelease(String userID){
        return blService.getReleased(userID);
    }

    @RequestMapping("/getInterestedPost")
    public @ResponseBody
    List<Mine> getInterestedPost(String userID){
        return blService.getInterestedpost(userID);
    }

    /*@RequestMapping("/getCollect")
    public @ResponseBody
    List<Mine> getCollect(String userID){
        return blService.getCollected(userID);
    }*/

    @RequestMapping("/getInterestedUser")
    public @ResponseBody
    List<Mine> getInterestedUser(String userID){
        return blService.getInterestedUser(userID);
    }

    @RequestMapping("/getHistory")
    public @ResponseBody
    List<String> getHistory(String userID){
        return blService.getHistory(userID);
    }
}
