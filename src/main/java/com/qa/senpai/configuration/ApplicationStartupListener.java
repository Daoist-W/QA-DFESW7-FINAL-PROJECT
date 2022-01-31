package com.qa.senpai.configuration;

import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.JobRepository;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.data.support.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

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
        userRepository.saveAll(List.of(
                new User(
                        Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", "132156654"
                ),
                new User(
                        Position.staff, "harry", "lerrt",
                        LocalDate.of(1991,9,15),
                        "harry@youmail.com", "+4475649589", "123465"
                ),

                new User(
                        Position.staff, "paris", "lorem",
                        LocalDate.of(1991,7,21),
                        "paris@youmail.com", "+4475649589", "79846545"
                ),

                new User(
                        Position.admin, "don", "isiko",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", "654821658"
                )
        ));
    }
}
