package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.services.AvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(AvailabilityController.class)
class AvailabilityControllerWebIntegrationTest {

    @Autowired // field injection
    private AvailabilityController availabilityController;

    @MockBean
    private AvailabilityService availabilityService;

    // Test variables set up
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
    private Availability setDateRange;

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
        availabilitiesDTO.addAll(List.of(
                new AvailabilityDTO(
                        1L,
                        LocalDate.of(2022, 1, 1),
                        LocalDate.of(2022, 1, 7)
                ),
                new AvailabilityDTO(
                        2L,
                        LocalDate.of(2022, 1, 8),
                        LocalDate.of(2022, 1, 14)
                ),
                new AvailabilityDTO(
                        3L,
                        LocalDate.of(2022, 1, 15),
                        LocalDate.of(2022, 1, 21)
                ),
                new AvailabilityDTO(
                        4L,
                        LocalDate.of(2022, 1, 22),
                        LocalDate.of(2022, 1, 28)
                )
        ));

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

        setDateRange = new Availability(
                availabilityStartDate,
                availabilityEndDate);

        availabilityToBeSaved = new Availability(
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21)
        );

        savedAvailability = new Availability(
                5L,
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21)
        );

        savedAvailabilityDTO = new AvailabilityDTO(
                5L,
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21)
        );


    }

    @Test
    void getAllAvailabilityTest() {
        when(availabilityService.getAll()).thenReturn(availabilitiesDTO);
        assertThat(availabilityController.getAllAvailability())
                .isEqualTo(ResponseEntity.ok(availabilitiesDTO));
    }

    @Test
    void getAvailabilityByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/availability/" + availabilityId);
        when(availabilityService.getById(availabilityId))
                .thenReturn(expectedAvailabilityWithIdDTO);
        assertThat(availabilityController.getAvailabilityById(availabilityId))
                .isEqualTo(new ResponseEntity<>(expectedAvailabilityWithIdDTO, headers, HttpStatus.OK));

    }


    @Test
    void getAvailabilityByDatesTest() {
        when(availabilityService.getByDates(availabilityStartDate, availabilityEndDate))
                .thenReturn(availabilityFoundListDTO);
        assertThat(availabilityController
                .getAvailabilitiesByDates(setDateRange))
                .isEqualTo(ResponseEntity.ok(availabilityFoundListDTO));

    }

    @Test
    void createAvailabilityTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/availability/create/" + savedAvailabilityDTO.getId());
        when(availabilityService.create(availabilityToBeSaved))
                .thenReturn(savedAvailabilityDTO);
        assertThat(availabilityController.createAvailability(availabilityToBeSaved))
                .isEqualTo(new ResponseEntity<>(savedAvailabilityDTO, headers, HttpStatus.CREATED));
    }

    @Test
    void updateAvailabilityByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/update/" + updatedAvailabilityDTO.getId());
        when(availabilityService.update(availabilityId, availabilityToUpdate))
                .thenReturn(updatedAvailabilityDTO);
        assertThat(availabilityController.updateAvailabilityById(availabilityId, availabilityToUpdate))
                .isEqualTo(new ResponseEntity<>(updatedAvailabilityDTO, headers, HttpStatus.OK));

    }

    @Test
    void deleteAvailabilityByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/delete/" + availabilityToDeleteDTO.getId());
        when(availabilityService.delete(availabilityId))
                .thenReturn(availabilityToDeleteDTO);
        assertThat(availabilityController.deleteAvailabilityById(availabilityId))
                .isEqualTo(new ResponseEntity<>(availabilityToDeleteDTO, headers, HttpStatus.OK));

    }

}
