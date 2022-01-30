package com.qa.senpai.data.repositories;

import com.qa.senpai.data.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
