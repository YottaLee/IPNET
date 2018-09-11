package com.ipnet.dao;

import com.ipnet.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;

@Repository
@Table(name = "evaluation")
public interface EvaluationDao extends JpaRepository<Evaluation,Long> {

    @Query(value = "select e from Evaluation e where e.patentID=:patentID order by e.time desc")
    ArrayList<Evaluation> findByPatentIDSortByTime(@Param(value = "patentID") String patentID);
}
