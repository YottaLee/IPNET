package com.ipnet.bl.messagebl;

import com.ipnet.blservice.MessageBLService;
import com.ipnet.dao.MessageDao;
import com.ipnet.entity.Message;
import com.ipnet.vo.MessageSetRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class MessageBL implements MessageBLService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public void generateMessage(String receiver, String event) {
        boolean isRead=false;
        Message message=new Message(receiver,event,isRead);
        messageDao.saveAndFlush(message);
    }

    @Override
    public void generateMessage(ArrayList<String> receiver, String event) {
        for(String r:receiver){
            boolean isRead=false;
            Message message=new Message(r,event,isRead);
            messageDao.saveAndFlush(message);
        }
    }

    @Override
    public void setRead(MessageSetRead messageSetRead) {
        for(String messageTime:messageSetRead.getMessageTimeList()){
            Message m=this.getOne(messageSetRead.getUsername(),messageTime);
            m.setRead(true);
            messageDao.saveAndFlush(m);
        }
    }

    @Override
    public ArrayList<Message> getAllMessageList(String username) {
        ArrayList<Message> messages=new ArrayList<>(messageDao.getMessageArray(username));
        return messages;
    }

    @Override
    public ArrayList<Message> getReadMessageList(String username) {
        ArrayList<Message> messages=messageDao.getReadMessageArray(username);
        return messages;
    }

    @Override
    public ArrayList<Message> getUnreadMessageList(String username) {
        ArrayList<Message> messages=messageDao.getUnReadMessageArray(username);
        return messages;
    }

    private Message getOne(String receiver,String time){
        Message message=messageDao.getMessage(receiver,time);
        return message;
    }

}
