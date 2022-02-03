package com.qa.senpai.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Jobs")
public class Job {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description_;

    @NotNull
    private String location;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    // TODO: implement entity relations with User


    public Job() {
    }


    public Job(Long id,
               String title,
               String description_,
               String location,
               LocalDate startDate,
               LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.description_ = description_;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Job(String title,
               String description_,
               String location,
               LocalDate startDate,
               LocalDate endDate) {
        this.title = title;
        this.description_ = description_;
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

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return getId().equals(job.getId()) &&
                getTitle().equals(job.getTitle()) &&
                getDescription_().equals(job.getDescription_()) &&
                getLocation().equals(job.getLocation()) &&
                getStartDate().equals(job.getStartDate()) &&
                getEndDate().equals(job.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getTitle(),
                getDescription_(),
                getLocation(),
                getStartDate(),
                getEndDate());
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description_='" + description_ + '\'' +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
