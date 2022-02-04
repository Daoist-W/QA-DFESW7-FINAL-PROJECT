package com.qa.senpai.configuration;

import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Profile("production")
@Configuration
public class AvailabilityApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private AvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityApplicationStartupListener(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }



    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {


        availabilityRepository.saveAll(List.of(
                new Availability(1L,
                        LocalDate.of(2022, 1, 1),
                        LocalDate.of(2022, 1, 7),
                        null
                ),
                new Availability(2L,
                        LocalDate.of(2022, 1, 8),
                        LocalDate.of(2022, 1, 14),
                        null
                ),
                new Availability(3L,
                        LocalDate.of(2022, 1, 15),
                        LocalDate.of(2022, 1, 21),
                        null
                ),
                new Availability(4L,
                        LocalDate.of(2022, 1, 22),
                        LocalDate.of(2022, 1, 29),
                        null
                )
        ));
    }
}
