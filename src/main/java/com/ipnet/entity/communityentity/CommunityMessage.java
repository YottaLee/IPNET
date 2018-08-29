package com.ipnet.entity.communityentity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "com_message")
@Entity
@AllArgsConstructor
public class CommunityMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mid;
    private String receiver;
    private String event;
    private String time;
    private boolean isRead;

    public CommunityMessage(){}

}
