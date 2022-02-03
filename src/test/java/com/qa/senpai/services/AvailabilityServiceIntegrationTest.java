package com.qa.senpai.services;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Sql(scripts = { "classpath:schema.sql",
        "classpath:data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AvailabilityServiceIntegrationTest {
    // Using springBootTest for unit integration testing
    // we need the Repository layer to be in the application context
    // and fully functional

    // Fields
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private AvailabilityService availabilityService;

    private List<Availability> availabilitiesInDatabase;
    private List<AvailabilityDTO> availabilitiesInDatabaseDTO;

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
    private Long nextNewElementsId;


    @BeforeEach
    void setUp() { // runs before every test

        availabilitiesInDatabase = new ArrayList<>();
        availabilitiesInDatabase.addAll(availabilityRepository.findAll());

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
        nextNewElementsId = availabilitiesInDatabase.get(size - 1).getId() + 1;

        availabilityId = 3L;

        expectedAvailabilityWithId = availabilitiesInDatabase.get(2);
        expectedAvailabilityWithIdDTO = availabilitiesInDatabaseDTO.get(2);
        expectedAvailabilityWithoutId = new Availability(
                LocalDate.of(2022, 1, 15),
                LocalDate.of(2022, 1, 21)
        );

        availabilityFoundList = List.of(availabilitiesInDatabase.get(1), availabilitiesInDatabase.get(2));
        availabilityFoundListDTO = List.of(availabilitiesInDatabaseDTO.get(1), availabilitiesInDatabaseDTO.get(2));

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

//        availabilityToBeSaved = new Availability(
//                LocalDate.of(3000, 1, 15),
//                LocalDate.of(3000, 1, 21)
//        );

        savedAvailability = new Availability(
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
        assertThat(availabilityService.create(savedAvailability))
                .isEqualTo(savedAvailabilityDTO);
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

