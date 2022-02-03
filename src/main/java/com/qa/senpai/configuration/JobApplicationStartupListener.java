package com.qa.senpai.configuration;

import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Profile("dev")
@Configuration
public class JobApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private JobRepository jobRepository;

    @Autowired
    public JobApplicationStartupListener(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }



    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {


        jobRepository.saveAll(List.of(
                new Job(1L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4),
                        null
                ),
                new Job(2L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4),
                        null
                ),
                new Job(3L,
                        "topjob3",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 6),
                        null
                ),
                new Job(4L,
                        "topjob4",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 12),
                        null
                )
        ));
    }
}
