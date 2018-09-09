package com.ipnet.dao;

import com.ipnet.entity.PersonalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;


@Repository
@Table(name = "personal_user")
public interface PersonalUserDao extends JpaRepository<PersonalUser,String> {

    PersonalUser findPersonalUserById(String id);

    PersonalUser findPersonalUserByName(String name);

}

