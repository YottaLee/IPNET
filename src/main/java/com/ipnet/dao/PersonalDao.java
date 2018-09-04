package com.ipnet.dao;

import com.ipnet.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;


@Repository
@Table(name = "personal_user")
public interface PersonalDao extends JpaRepository<Person,String> {

}
