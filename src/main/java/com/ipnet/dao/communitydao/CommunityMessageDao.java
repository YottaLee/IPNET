package com.ipnet.dao.communitydao;

import com.ipnet.entity.communityentity.CommunityMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;

@Repository
@Table(name = "com_message")
public interface CommunityMessageDao extends JpaRepository<CommunityMessage,Long> {

    @Query(value = "select m from CommunityMessage m where m.receiver=:username and m.time=:t")
    CommunityMessage findByUserAndTime(@Param(value = "username") String username, @Param(value = "t") String time);

    @Query(value = "select m from CommunityMessage m where m.receiver=:username order by m.time desc")
    ArrayList<CommunityMessage> findAllSortByTime(@Param(value = "username") String username);

    @Query(value = "select m from CommunityMessage m where m.receiver=:username and m.isRead=false order by m.time desc")
    ArrayList<CommunityMessage> findUnreadSortByTime(@Param(value = "username") String username);
}
