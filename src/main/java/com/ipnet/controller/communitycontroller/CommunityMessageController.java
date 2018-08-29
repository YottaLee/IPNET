package com.ipnet.controller.communitycontroller;

import com.ipnet.blservice.communityservice.CommunityMessageBLService;
import com.ipnet.entity.communityentity.CommunityMessage;
import com.ipnet.vo.MessageSetRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/C_Message")
public class CommunityMessageController {

    @Autowired
    private CommunityMessageBLService communityMessageBLService;

    @RequestMapping("/generateMessage")
    public @ResponseBody
    void addMessage(@RequestParam String receiver, @RequestParam String event){
        communityMessageBLService.generateMessage(receiver,event);
    }

    /*
    * 描述：同时为多人生成消息
    * 用途：仅用于测试*/
    @RequestMapping("/generateManyMessage")
    public @ResponseBody
    void addManyMessage(@RequestParam String event){
        ArrayList<String> receivers=new ArrayList<>();
        receivers.add("jane");
        receivers.add("jane");
        receivers.add("jane");
        communityMessageBLService.generateMessage(receivers,event);
    }

    @RequestMapping("/showAll")
    public @ResponseBody
    ArrayList<CommunityMessage> showAllMessage(@RequestParam String username){
        return communityMessageBLService.getAllMessageList(username);
    }

    @RequestMapping("/showUnRead")
    public @ResponseBody
    ArrayList<CommunityMessage> showUnReadMessage(@RequestParam String username){
        return communityMessageBLService.getUnreadMessageList(username);
    }

    @RequestMapping("/setRead")
    public @ResponseBody
    void setRead(@RequestBody MessageSetRead messageSetRead){
        communityMessageBLService.setRead(messageSetRead);
    }
}
