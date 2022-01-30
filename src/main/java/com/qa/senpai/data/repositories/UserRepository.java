package com.qa.senpai.data.repositories;

import com.qa.senpai.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
