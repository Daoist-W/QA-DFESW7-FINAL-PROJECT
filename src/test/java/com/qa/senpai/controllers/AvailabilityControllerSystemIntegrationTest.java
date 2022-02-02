package com.qa.senpai.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@AutoConfigureMockMvc // configure the MockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvailabilityControllerSystemIntegrationTest {

    // Fields
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AvailabilityRepository availabilityRepository;

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
    private Availability setDateRange;


    @BeforeEach
    void setUp() { // runs before every test
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

        setDateRange = new Availability(
                availabilityStartDate,
                availabilityEndDate);

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
    void getAllAvailabilityTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/availability");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(availabilitiesInDatabaseDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void getAvailabilityByIdTest() throws Exception{
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/availability/" + expectedAvailabilityWithIdDTO.getId());
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String job = objectMapper.writeValueAsString(expectedAvailabilityWithIdDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(job);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

    @Test
    void getAvailabilitiesByDates() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/availability/dates");
        mockRequest.accept(MediaType.APPLICATION_JSON);
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(setDateRange));

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(availabilityFoundListDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);

    }
    @Test
    void createAvailabilityTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/availability/create/");
        mockRequest.accept(MediaType.APPLICATION_JSON);
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(availabilityToBeSaved));

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(savedAvailabilityDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);


    }
    @Test
    void updateAvailabilityByIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/availability/update/" + updatedAvailability.getId());
        mockRequest.accept(MediaType.APPLICATION_JSON);
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(availabilityToUpdate));

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(updatedAvailabilityDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void deleteAvailabilityByIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.DELETE, "/availability/admin/delete/" + availabilityToDelete.getId());
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String job = objectMapper.writeValueAsString(availabilityToDeleteDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(job);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }


}
