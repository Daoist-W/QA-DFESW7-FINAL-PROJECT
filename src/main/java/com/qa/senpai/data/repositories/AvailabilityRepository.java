package com.qa.senpai.data.repositories;

import com.qa.senpai.data.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {


    // simplified scheduling for Job entity
    @Query("SELECT a FROM Availability a WHERE a.startDate >= ?1 AND a.endDate <= ?2")
    List<Availability> findByDates(
            @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
            @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate
    );

    // we are looking at ta field in the class
    public List<Availability> findByUserId(Long userId);
}
