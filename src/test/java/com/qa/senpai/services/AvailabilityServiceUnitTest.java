package com.qa.senpai.services;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceUnitTest {
    // Using mockito only for this unit test
    // there is no need for generating application contexts
    // and so Mockito is a suitable testing choice

    // Fields
    @Mock
    private AvailabilityRepository availabilityRepository;

    @Mock
    private ModelMapper availabilityMapper;

    @InjectMocks
    // this creates an instance of the Service class and
    // injects the ModelMapper and Repository mock instances
    private AvailabilityService availabilityService;

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
    private Long userId;


    @BeforeEach
    void setUp() { // runs before every test
        availabilities = new ArrayList<>();
        availabilities.addAll(List.of(
                new Availability(
                        1L,
                        LocalDate.of(2022, 1, 1),
                        LocalDate.of(2022, 1, 7),
                        null
                ),
                new Availability(
                        2L,
                        LocalDate.of(2022, 1, 8),
                        LocalDate.of(2022, 1, 14),
                        null
                ),
                new Availability(
                        3L,
                        LocalDate.of(2022, 1, 15),
                        LocalDate.of(2022, 1, 21),
                        null
                ),
                new Availability(
                        4L,
                        LocalDate.of(2022, 1, 22),
                        LocalDate.of(2022, 1, 28),
                        null
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
        userId =  1L;

        expectedAvailabilityWithId = availabilities.get(2);
        expectedAvailabilityWithIdDTO = availabilitiesDTO.get(2);
        expectedAvailabilityWithoutId = new Availability(
                    LocalDate.of(2022, 1, 15),
                    LocalDate.of(2022, 1, 21),
                null
        );

        availabilityFoundList = List.of(availabilities.get(1), availabilities.get(2));
        availabilityFoundListDTO = List.of(availabilitiesDTO.get(1), availabilitiesDTO.get(2));

        availabilityToUpdate = new Availability(
                3L,
                LocalDate.of(1111, 1, 15),
                LocalDate.of(2222, 1, 21),
                null
        );
        updatedAvailability = new Availability(
                3L,
                LocalDate.of(1111, 1, 15),
                LocalDate.of(2222, 1, 21),
                null
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
                LocalDate.of(3000, 1, 21),
                null
        );

        savedAvailability = new Availability(
                5L,
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21),
                null
        );

        savedAvailabilityDTO = new AvailabilityDTO(
                5L,
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21)
        );


    }

    @Test
    void getAllTest() {
        when(availabilityRepository.findAll()).thenReturn(availabilities);
        for(int i = 0; i < availabilities.size(); i++) {
            when(availabilityMapper.map(availabilities.get(i), AvailabilityDTO.class))
                    .thenReturn(availabilitiesDTO.get(i));
        }
        // assertThat()
        assertThat(availabilityService.getAll()).isEqualTo(availabilitiesDTO);
        // verify()
        verify(availabilityRepository).findAll();
        for (Availability availability : availabilities) {
            verify(availabilityMapper).map(availability, AvailabilityDTO.class);
        }

    }

    @Test
    void getByIdTest() {
        when(availabilityRepository.existsById(availabilityId)).thenReturn(true);
        when(availabilityRepository.getById(availabilityId)).thenReturn(expectedAvailabilityWithId);
        when(availabilityMapper.map(expectedAvailabilityWithId, AvailabilityDTO.class))
                .thenReturn(expectedAvailabilityWithIdDTO);
        // assertThat()
        assertThat(availabilityService.getById(availabilityId)).isEqualTo(expectedAvailabilityWithIdDTO);
        // verify()
        verify(availabilityRepository).existsById(availabilityId);
        verify(availabilityRepository).getById(availabilityId);
        verify(availabilityMapper).map(expectedAvailabilityWithId, AvailabilityDTO.class);

    }

    @Test
    void getByUserIdTest() {
        when(availabilityRepository.findByUserId(userId)).thenReturn(availabilityFoundList);
        for(int i = 0; i < availabilityFoundList.size(); i++) {
            when(availabilityMapper.map(availabilityFoundList.get(i), AvailabilityDTO.class))
                    .thenReturn(availabilityFoundListDTO.get(i));
        }
        // assertThat()
        assertThat(availabilityService.getByUserId(userId)).isEqualTo(availabilityFoundListDTO);
        // verify()
        verify(availabilityRepository).findByUserId(userId);
        for (Availability availability : availabilityFoundList) {
            verify(availabilityMapper).map(availability, AvailabilityDTO.class);
        }
    }

    @Test
    void getByDatesTest() {
        when(availabilityRepository
                .findByDates(availabilityStartDate, availabilityEndDate))
                .thenReturn(availabilityFoundList);

        for(int i = 0; i < availabilityFoundList.size(); i++) {
            when(availabilityMapper
                    .map(availabilityFoundList.get(i), AvailabilityDTO.class))
                    .thenReturn(availabilityFoundListDTO.get(i));
        }
        // assertThat()
        assertThat(availabilityService.getByDates(
                availabilityStartDate,
                availabilityEndDate))
                .isEqualTo(availabilityFoundListDTO);

        // verify()
        verify(availabilityRepository)
                .findByDates(availabilityStartDate, availabilityEndDate);
        for (Availability availability : availabilityFoundList) {
            verify(availabilityMapper).map(availability, AvailabilityDTO.class);
        }
    }

    @Test
    void createTest() {
        when(availabilityRepository.save(availabilityToBeSaved)).thenReturn(savedAvailability);
        when(availabilityMapper.map(savedAvailability, AvailabilityDTO.class)).thenReturn(savedAvailabilityDTO);
        // assertThat()
        assertThat(availabilityService.create(availabilityToBeSaved)).isEqualTo(savedAvailabilityDTO);
        // verify()
        verify(availabilityRepository).save(availabilityToBeSaved);
    }

    @Test
    void updateTest() {
        when(availabilityRepository.existsById(availabilityId)).thenReturn(true);
        when(availabilityRepository.getById(availabilityId)).thenReturn(availabilityToUpdate);
        when(availabilityRepository.save(availabilityToUpdate)).thenReturn(updatedAvailability);
        when(availabilityMapper.map(updatedAvailability, AvailabilityDTO.class)).thenReturn(updatedAvailabilityDTO);
        // assertThat()
        assertThat(availabilityService.update(availabilityId, availabilityToUpdate)).isEqualTo(updatedAvailabilityDTO);
        // verify()
        verify(availabilityRepository).existsById(availabilityId);
        verify(availabilityRepository).getById(availabilityId);
        verify(availabilityRepository).save(availabilityToUpdate);
        verify(availabilityMapper).map(updatedAvailability, AvailabilityDTO.class);
    }

    @Test
    void deleteTest() {
        when(availabilityRepository.existsById(availabilityId)).thenReturn(true);
        when(availabilityRepository.getById(availabilityId)).thenReturn(availabilityToDelete);
        when(availabilityMapper.map(availabilityToDelete, AvailabilityDTO.class)).thenReturn(availabilityToDeleteDTO);
        // assertThat()
        assertThat(availabilityService.delete(availabilityId)).isEqualTo(availabilityToDeleteDTO);
        // verify()
        verify(availabilityRepository).existsById(availabilityId);
        verify(availabilityRepository).getById(availabilityId);
        verify(availabilityRepository).deleteById(availabilityId);
    }
}
