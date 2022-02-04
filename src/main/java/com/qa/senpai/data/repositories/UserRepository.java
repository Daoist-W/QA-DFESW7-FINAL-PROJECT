package com.qa.senpai.data.repositories;

import com.qa.senpai.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT a FROM User a WHERE a.forename = ?1 AND a.surname = ?2")
    List<User> findByFullName(String forename, String surname);
}
