package com.qa.senpai.data.dtos;

import java.time.LocalDate;

public class AvailabilityDTO {

    // fields
    Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    public AvailabilityDTO() {

    }

    public AvailabilityDTO(Long id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
