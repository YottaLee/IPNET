package com.ipnet.dao;

import com.ipnet.entity.Patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * @author lzb
 * @date 2018/7/21 10:47
 */
@Repository
@Table(name = "patent")
public interface PatentDao extends JpaRepository<Patent,String>{


    @Query(value = "select p from Patent p where p.patent_name = :patentName")
    List<Patent> searchPatentByPatentName(@Param("patentName") String patentName);


}
