package com.ipnet.dao;

import com.ipnet.entity.Rec.PatentOrManagerRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
public interface PatentOrManagerRecDao extends JpaRepository<PatentOrManagerRec,String> {

    @Query(value = "select p from PatentOrManagerRec p where p.type=?1")
    List<PatentOrManagerRec> findPatentOrManagerRecsByType(int type);


}
