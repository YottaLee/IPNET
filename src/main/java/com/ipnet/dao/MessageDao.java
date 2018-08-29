package com.ipnet.dao;

import com.ipnet.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;

@Repository
@Table(name = "message")
public interface MessageDao extends JpaRepository<Message,Long>{

    @Query(value = "select m from Message m where m.receiver =:receiver and m.time =:time")
    Message getMessage(@Param("receiver") String receiver, @Param("time") String time);

    @Query(value = "select m from Message m where m.receiver =:receiver")
    ArrayList<Message> getMessageArray(@Param("receiver") String receiver);

    @Query(value = "select m from Message m where m.receiver =:receiver and m.isRead=true ")
    ArrayList<Message> getReadMessageArray(@Param("receiver") String receiver);

    @Query(value = "select m from Message m where m.receiver =:receiver and m.isRead=false ")
    ArrayList<Message> getUnReadMessageArray(@Param("receiver") String receiver);
}
