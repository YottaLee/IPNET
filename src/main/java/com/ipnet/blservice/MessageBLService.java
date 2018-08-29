package com.ipnet.blservice;

import com.ipnet.entity.Message;
import com.ipnet.vo.MessageSetRead;

import java.util.ArrayList;

public interface MessageBLService {
    void generateMessage(String receiver, String event);
    void generateMessage(ArrayList<String> receiver, String event);

    void setRead(MessageSetRead messageSetRead);

    ArrayList<Message> getAllMessageList(String username);
    ArrayList<Message> getReadMessageList(String username);
    ArrayList<Message> getUnreadMessageList(String username);
}
