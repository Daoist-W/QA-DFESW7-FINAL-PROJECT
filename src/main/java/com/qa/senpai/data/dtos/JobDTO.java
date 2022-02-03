package com.qa.senpai.data.dtos;

import com.qa.senpai.data.entities.User;

import java.time.LocalDate;
import java.util.Objects;

public class JobDTO {

    Long id;
    String title;
    String description;
    String location;
    private LocalDate startDate;
    private LocalDate endDate;

    private User user;

    public JobDTO() {

    }

    public JobDTO(Long id,
                  String title,
                  String description,
                  String location,
                  LocalDate startDate,
                  LocalDate endDate,
                  User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public JobDTO(Long id,
                  String title,
                  String description,
                  String location,
                  LocalDate startDate,
                  LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobDTO jobDTO = (JobDTO) o;
        return getId().equals(jobDTO.getId()) && getTitle().equals(jobDTO.getTitle()) && getDescription().equals(jobDTO.getDescription()) && getLocation().equals(jobDTO.getLocation()) && getStartDate().equals(jobDTO.getStartDate()) && getEndDate().equals(jobDTO.getEndDate()) && Objects.equals(getUser(), jobDTO.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getLocation(), getStartDate(), getEndDate(), getUser());
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                '}';
    }
}
