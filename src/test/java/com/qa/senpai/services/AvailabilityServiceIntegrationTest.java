package com.qa.senpai.services;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AvailabilityServiceIntegrationTest {
    // Using mockito only for this unit test

    // Fields
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private AvailabilityService availabilityService;

    private List<Availability> availabilitiesInDatabase;
    private List<AvailabilityDTO> availabilitiesInDatabaseDTO;

    private List<Availability> availabilities;
    private List<AvailabilityDTO> availabilitiesDTO;

    private Long availabilityId;
    private Availability expectedAvailabilityWithId;
    private Availability expectedAvailabilityWithoutId;
    private AvailabilityDTO expectedAvailabilityWithIdDTO;

    private List<Availability> availabilityFoundList;
    private List<AvailabilityDTO> availabilityFoundListDTO;

    private Availability availabilityToUpdate;
    private Availability updatedAvailability;
    private AvailabilityDTO updatedAvailabilityDTO;

    private Availability availabilityToDelete;
    private AvailabilityDTO availabilityToDeleteDTO;

    private LocalDate availabilityStartDate;
    private LocalDate availabilityEndDate;

    private Availability availabilityToBeSaved;
    private Availability savedAvailability;
    private AvailabilityDTO savedAvailabilityDTO;


    @BeforeEach
    void setUp() { // runs before every test
        // TODO: implement me
        availabilities = new ArrayList<>();
        availabilities.addAll(List.of(
                new Availability(
                        1L,
                        LocalDate.of(2022, 1, 1),
                        LocalDate.of(2022, 1, 7)
                ),
                new Availability(
                        2L,
                        LocalDate.of(2022, 1, 8),
                        LocalDate.of(2022, 1, 14)
                ),
                new Availability(
                        3L,
                        LocalDate.of(2022, 1, 15),
                        LocalDate.of(2022, 1, 21)
                ),
                new Availability(
                        4L,
                        LocalDate.of(2022, 1, 22),
                        LocalDate.of(2022, 1, 28)
                )
        ));

        availabilitiesDTO = new ArrayList<>();
        for(Availability availability : availabilities) {
            availabilitiesDTO.add(
                    new AvailabilityDTO(
                            availability.getId(),
                            availability.getStartDate(),
                            availability.getEndDate()
                    )
            );
        }

        availabilitiesInDatabase = new ArrayList<>();
        availabilitiesInDatabase.addAll(availabilityRepository.saveAll(availabilities));

        availabilitiesInDatabaseDTO = new ArrayList<>();
        for(Availability availability : availabilitiesInDatabase) {
            availabilitiesInDatabaseDTO.add(
                    new AvailabilityDTO(
                            availability.getId(),
                            availability.getStartDate(),
                            availability.getEndDate()
                    )
            );
        }

        int size = availabilitiesInDatabase.size();
        Long nextNewElementsId = availabilitiesInDatabase.get(size - 1).getId() + 1;

        availabilityId = 3L;

        expectedAvailabilityWithId = availabilities.get(2);
        expectedAvailabilityWithIdDTO = availabilitiesDTO.get(2);
        expectedAvailabilityWithoutId = new Availability(
                LocalDate.of(2022, 1, 15),
                LocalDate.of(2022, 1, 21)
        );

        availabilityFoundList = List.of(availabilities.get(1), availabilities.get(2));
        availabilityFoundListDTO = List.of(availabilitiesDTO.get(1), availabilitiesDTO.get(2));

        availabilityToUpdate = new Availability(
                3L,
                LocalDate.of(1111, 1, 15),
                LocalDate.of(2222, 1, 21)
        );
        updatedAvailability = new Availability(
                3L,
                LocalDate.of(1111, 1, 15),
                LocalDate.of(2222, 1, 21)
        );
        updatedAvailabilityDTO = new AvailabilityDTO(
                3L,
                LocalDate.of(1111, 1, 15),
                LocalDate.of(2222, 1, 21)
        );

        // this is done for clarity
        availabilityToDelete = expectedAvailabilityWithId;
        availabilityToDeleteDTO = expectedAvailabilityWithIdDTO;

        availabilityStartDate = LocalDate.of(2022, 1, 7);
        availabilityEndDate = LocalDate.of(2022, 1, 22);

        availabilityToBeSaved = new Availability(
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21)
        );

        savedAvailability = new Availability(
                nextNewElementsId,
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21)
        );

        savedAvailabilityDTO = new AvailabilityDTO(
                nextNewElementsId,
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21)
        );


    }

    @Test
    void getAll() {
        assertThat(availabilityService.getAll())
                .isEqualTo(availabilitiesInDatabaseDTO);
    }

    @Test
    void getById() {
        assertThat(availabilityService
                .getById(availabilityId))
                .isEqualTo(expectedAvailabilityWithIdDTO);
    }

    @Test
    void getByDates() {
        assertThat(availabilityService
                .getByDates(availabilityStartDate, availabilityEndDate))
                .isEqualTo(availabilityFoundListDTO);
    }

    @Test
    void create() {
        assertThat(savedAvailabilityDTO)
                .isEqualTo(availabilityService.create(availabilityToBeSaved));
    }

    @Test
    void update() {
        assertThat(availabilityService.update(availabilityId, availabilityToUpdate))
                .isEqualTo(updatedAvailabilityDTO);
    }

    @Test
    void delete() {
        assertThat(availabilityService.delete(availabilityId)).isEqualTo(availabilityToDeleteDTO);
    }
}
