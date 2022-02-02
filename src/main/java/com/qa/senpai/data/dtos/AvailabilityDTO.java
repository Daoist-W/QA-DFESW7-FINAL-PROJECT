package com.qa.senpai.data.dtos;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityDTO that = (AvailabilityDTO) o;
        return getId().equals(that.getId()) && getStartDate().equals(that.getStartDate()) && getEndDate().equals(that.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartDate(), getEndDate());
    }
}
