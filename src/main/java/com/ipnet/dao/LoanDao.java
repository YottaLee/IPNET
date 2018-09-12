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

    @Query(value = "select l from Loan l order by l.time desc")
    ArrayList<Loan> findAllSortByTime();

    @Query(value = "select l from Loan l where l.insurance=:insurance order by l.time desc")
    ArrayList<Loan> findByInsuranceSortByTime(@Param("insurance")String insurance);

    @Query(value = "select l from Loan l where l.bank=:bank order by l.time desc")
    ArrayList<Loan> findByBankSortByTime(@Param("bank")String bank);
}
