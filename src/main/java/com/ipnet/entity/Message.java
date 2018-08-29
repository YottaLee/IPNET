package com.ipnet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "message")
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mid;
    private String receiver;
    private String event;
    private String time;
    private boolean isRead;

    public Message(){}

    public Message(String receiver,String event,boolean isRead){
        this.receiver=receiver;
        this.event=event;
        this.isRead=isRead;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time=df.format(new Date());
    }
}
