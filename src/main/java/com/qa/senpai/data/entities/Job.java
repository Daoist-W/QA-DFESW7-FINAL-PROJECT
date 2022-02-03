package com.qa.senpai.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    // many to one
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")// this isa JPA implementation suitable for Hibernate
    @JsonBackReference // avoids recursive output
    private User user;


    public Job() {
    }


    public Job(Long id,
               String title,
               String description_,
               String location,
               LocalDate startDate,
               LocalDate endDate,
               User user) {
        this.id = id;
        this.title = title;
        this.description_ = description_;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public Job(String title,
               String description_,
               String location,
               LocalDate startDate,
               LocalDate endDate,
               User user) {
        this.title = title;
        this.description_ = description_;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
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
        Job job = (Job) o;
        return getId().equals(job.getId()) && getTitle().equals(job.getTitle()) && getDescription_().equals(job.getDescription_()) && getLocation().equals(job.getLocation()) && getStartDate().equals(job.getStartDate()) && getEndDate().equals(job.getEndDate()) && Objects.equals(getUser(), job.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription_(), getLocation(), getStartDate(), getEndDate(), getUser());
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
                ", user=" + user +
                '}';
    }
}
