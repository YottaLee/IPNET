package com.ipnet.dao;

import com.ipnet.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;


@Repository
@Table(name = "person")
public interface PersonalDao extends JpaRepository<Person,String> {
    @Query(value = "select t from Person t where t.username =:username")
    Person searchUserById(@Param("username") String username);
}
