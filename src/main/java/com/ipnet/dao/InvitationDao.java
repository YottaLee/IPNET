package com.ipnet.dao;

import com.ipnet.bl.patentbl.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * @author lzb
 * @date 2018/9/16 10:51
 */
@Repository
@Table(name = "invitation")
public interface InvitationDao extends JpaRepository<Invitation , Long> {
    @Query(value = "select I from Invitation I where I.patentId = :patentId")
    List<Invitation> searchInvitationByPatentId(@Param("patentId") String patentId);
}
