package com.ipnet.blservice.communityservice;

import com.ipnet.entity.communityentity.CommunityMessage;
import com.ipnet.vo.MessageSetRead;

import java.util.ArrayList;

public interface CommunityMessageBLService {

    void generateMessage(String receiver, String event);

    void generateMessage(ArrayList<String> receiver, String event);

    void setRead(MessageSetRead messageSetRead);

    ArrayList<CommunityMessage> getAllMessageList(String username);

    ArrayList<CommunityMessage> getUnreadMessageList(String username);
}
