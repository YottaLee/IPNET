package com.ipnet.dao;

import com.ipnet.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;

@Repository
@Table(name = "loan")
public interface LoanDao extends JpaRepository<Loan,String> {

    @Query(value = "select l from Loan l where l.state>=4 order by l.time desc")
    ArrayList<Loan> findAllSortByTime();

//    @Query(value = "select l from Loan l where l.insurance=:insurance and l.state>=7 order by l.time desc")
    @Query(value = "select l from Loan l where l.state>=7 order by l.time desc")
    ArrayList<Loan> findByInsuranceSortByTime(@Param("insurance")String insurance);

//    @Query(value = "select l from Loan l where l.bank=:bank and l.state>=5 order by l.time desc")
    @Query(value = "select l from Loan l where l.state>=1 order by l.time desc")
    ArrayList<Loan> findByBankSortByTime(@Param("bank")String bank);

    @Query(value = "select l from Loan l where l.patentID=:patentID order by l.time desc")
    ArrayList<Loan> findByPatentIDSortByTime(@Param("patentID")String patentID);

    @Query(value = "select l from Loan l where l.patentID=:patent")
    ArrayList<Loan> findByPatentID(@Param("patent")String patentid);
}
