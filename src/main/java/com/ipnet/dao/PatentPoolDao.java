package com.ipnet.dao;

import com.ipnet.entity.PatentPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * @author lzb
 * @date 2018/7/22 22:48
 */
@Repository
@Table(name = "pool")
public interface PatentPoolDao extends JpaRepository<PatentPool , String>{

    @Query(value = "select pool from PatentPool pool where pool.name = :poolName")
    List<PatentPool> searchPatentPoolByName(@Param("poolName") String poolName);

}
