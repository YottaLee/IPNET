package com.ipnet.controller;

import com.ipnet.blservice.MessageBLService;
import com.ipnet.entity.Message;
import com.ipnet.enums.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class MessageController {

    @Autowired
    private MessageBLService messageBLService;

    @RequestMapping("/addMessage")
    public @ResponseBody
    ResultMessage addMessage(@RequestParam String receiver,@RequestParam String event){
        messageBLService.generateMessage(receiver,event);
        return ResultMessage.Success;
    }

    @RequestMapping("/showAll")
    public @ResponseBody
    ArrayList<Message> showAllMessage(@RequestParam String username){
        return messageBLService.getAllMessageList(username);
    }

    @RequestMapping("/showRead")
    public @ResponseBody ArrayList<Message> showReadMessage(@RequestParam String username){
        return messageBLService.getReadMessageList(username);
    }

    @RequestMapping("/showUnRead")
    public @ResponseBody ArrayList<Message> showUnReadMessage(@RequestParam String username){
        return messageBLService.getUnreadMessageList(username);
    }
}
