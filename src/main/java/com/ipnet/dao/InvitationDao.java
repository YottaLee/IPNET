package com.ipnet.dao;

import com.ipnet.bl.patentbl.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * @author lzb
 * @date 2018/9/16 10:51
 */
@Repository
@Table(name = "invitation")
public interface InvitationDao extends JpaRepository<Invitation , String> {

}
