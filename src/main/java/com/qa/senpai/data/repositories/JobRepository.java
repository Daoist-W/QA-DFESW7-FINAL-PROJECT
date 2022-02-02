package com.qa.senpai.data.repositories;

import com.qa.senpai.data.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("FROM Job WHERE title = ?1")
    List<Job> findBytitle(String title);

    @Query("FROM Job WHERE location = ?1")
    List<Job> findByLocation(String location);

    // simplified scheduling for Job entity
    @Query("SELECT a FROM Job a WHERE a.startDate >= ?1 AND a.endDate <= ?2")
    List<Job> findByDates(
            @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
            @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate
    );
}
