package com.ipnet.dao;

import com.ipnet.entity.PersonalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;


@Repository
@Table(name = "personal_user")
public interface UserDao extends JpaRepository<PersonalUser,String> {

    @Query(value = "select t from PersonalUser t where t.username =:username")
    PersonalUser searchUserById(@Param("username") String username);
}
