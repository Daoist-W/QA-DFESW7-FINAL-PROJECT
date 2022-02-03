package com.qa.senpai.configuration;

import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.data.support.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Profile("devsql")
@Configuration
public class UserApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private UserRepository userRepository;

    @Autowired
    public UserApplicationStartupListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {


        userRepository.saveAll(List.of(
                new User(
                        1L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", 132156654, null),
                new User(
                        2L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "harry@youmail.com", "+4475649589", 123465, null),

                new User(
                        3L, Position.staff, "paris", "lorem",
                        LocalDate.of(1991,7,21),
                        "paris@youmail.com", "+4475649589", 79846545, null),

                new User(
                        4L, Position.admin, "don", "isiko",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", 654821658, null)

        ));
    }
}
