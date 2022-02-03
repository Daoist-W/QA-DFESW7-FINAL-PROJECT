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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// @Transactional disabled due to configuration issues, replaced with DirtiesContext
@AutoConfigureMockMvc // configure the MockMvc
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:schema.sql",
        "classpath:data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AvailabilityControllerSystemIntegrationTest {

    // Fields
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    // Test variables set up
    private List<Availability> availabilitiesInDatabase;
    private List<AvailabilityDTO> availabilitiesInDatabaseDTO;

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
    private Long userId;


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
        Long nextNewElementsId = availabilitiesInDatabase.get(size - 1).getId() + 1;

        availabilityId = 3L;
        userId = 1L;

        expectedAvailabilityWithId = availabilitiesInDatabase.get(2);
        expectedAvailabilityWithIdDTO = availabilitiesInDatabaseDTO.get(2);
        expectedAvailabilityWithoutId = new Availability(
                LocalDate.of(2022, 1, 15),
                LocalDate.of(2022, 1, 21),
                null
        );

        availabilityFoundList = List.of(availabilitiesInDatabase.get(1), availabilitiesInDatabase.get(2));
        availabilityFoundListDTO = List.of(availabilitiesInDatabaseDTO.get(1), availabilitiesInDatabaseDTO.get(2));

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

        setDateRange = new Availability(
                availabilityStartDate,
                availabilityEndDate,
                null);

        availabilityToBeSaved = new Availability(
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21),
                null
        );

        savedAvailability = new Availability(
                nextNewElementsId,
                LocalDate.of(3000, 1, 15),
                LocalDate.of(3000, 1, 21),
                null
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
                .request(HttpMethod.GET, "/availability/all");
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

    @Test
    void getAvailabilityByUserIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/availability/userid/" + userId);
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String availability = objectMapper.writeValueAsString(availabilityFoundListDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(availability);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

}
