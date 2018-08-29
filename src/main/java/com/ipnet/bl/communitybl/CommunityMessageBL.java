package com.ipnet.bl.communitybl;

import com.ipnet.blservice.communityservice.CommunityMessageBLService;
import com.ipnet.dao.communitydao.CommunityMessageDao;
import com.ipnet.entity.communityentity.CommunityMessage;
import com.ipnet.vo.MessageSetRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommunityMessageBL implements CommunityMessageBLService {

    @Autowired
    private CommunityMessageDao communityMessageDao;

    @Override
    public void generateMessage(String receiver, String event) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=df.format(new Date());
        CommunityMessage message=new CommunityMessage(0,receiver,event,time,false);
        communityMessageDao.saveAndFlush(message);
    }

    @Override
    public void generateMessage(ArrayList<String> receiver, String event) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=df.format(new Date());
        List<CommunityMessage> messages=new ArrayList<>();
        for(String s:receiver){
            CommunityMessage message=new CommunityMessage(0,s, event,time,false);
            messages.add(message);
        }
        communityMessageDao.saveAll(messages);
    }

    @Override
    public void setRead(MessageSetRead messageSetRead) {
        String username=messageSetRead.getUsername();
        for(String time:messageSetRead.getMessageTimeList()){
            CommunityMessage message=communityMessageDao.findByUserAndTime(username,time);
            message.setRead(true);
            communityMessageDao.saveAndFlush(message);
        }
    }

    @Override
    public ArrayList<CommunityMessage> getAllMessageList(String username) {
        return communityMessageDao.findAllSortByTime(username);
    }

    @Override
    public ArrayList<CommunityMessage> getUnreadMessageList(String username) {
        return communityMessageDao.findUnreadSortByTime(username);
    }
}
