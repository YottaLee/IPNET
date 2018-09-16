package com.ipnet.dao;

import com.ipnet.entity.Patent;
import com.ipnet.enums.Patent_state;
import com.ipnet.enums.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * @date 2018/7/21 10:47
 */
@Repository
@Table(name = "patent")
public interface PatentDao extends JpaRepository<Patent,String>{

    @Query(value = "select p from Patent p where p.patent_name like %?1% or p.pool_id like %?1% or p.patent_holder like %?1% or p.patent_id like %?1% or p.patent_type like %?1%")
    List<Patent> searchPatent(String info);

    @Query(value = "select p from Patent p where p.patent_name = :patentName")
    List<Patent> searchPatentByPatentName(@Param("patentName") String patentName);

    @Query(value = "select p from Patent p where p.patent_holder = :patent_holder")
    List<Patent> searchPatentByHolder(@Param("patent_holder") String patent_holder);

    @Query(value = "select p from Patent p where p.pool_id = :pool_id")
    List<Patent> searchRelatedPatents(@Param("pool_id") String pool_id);

    @Query(value = "select p from Patent p where p.state = :state")
    List<Patent> searchPatentsByState(@Param("state") Patent_state state);

    @Query(value = "select p from Patent p where p.valid_period = :valid_period")
    List<Patent> searchPatentsByValid_period(@Param("valid_period") String valid_period);

    @Query(value = "select p from Patent p where p.apply_date = :region")
    List<Patent> searchPatentsByRegion(@Param("region") String region);

    @Query(value = "select p from Patent p where p.userId = :userId")
    List<Patent> searchPatentsByUserId(@Param("userId") String userId);

    @Query(value = "select p from Patent p where p.patent_type = :patent_type")
    List<Patent> searchPatentsByType(@Param("patent_type") String patent_type);

}
