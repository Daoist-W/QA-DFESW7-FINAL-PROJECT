package com.qa.senpai.configuration;

import com.qa.senpai.data.repositories.JobRepository;
import com.qa.senpai.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private UserRepository userRepository;
    private JobRepository jobRepository;

    @Autowired
    public ApplicationStartupListener(UserRepository userRepository, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }



    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

    }
}
