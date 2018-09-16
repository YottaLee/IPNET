package com.ipnet.dao;

import com.ipnet.entity.PersonalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Date;


@Repository
@Table(name = "personal_user")
public interface PersonalUserDao extends JpaRepository<PersonalUser,String> {

    @Query(value = "select p from PersonalUser p where p.id=:pid")
    PersonalUser findPersonalUserById(@Param("pid") String pid);

    PersonalUser findPersonalUserByName(String name);

    @Query("select count(p) from PersonalUser p where p.registerTime<=:t")
    int count(@Param("t")Date date);

}

