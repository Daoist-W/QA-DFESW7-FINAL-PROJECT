package com.qa.senpai.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Job {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String title;

    @NotNull
    String description;

    @NotNull
    String location;

    // TODO: implement entity relations with User and Dates
    // TODO: create a entity/repo/service/controller suite for dates
//    List<LocalDate> dates;
//    User user;

    public Job(Long id, String title, String description, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public Job(String title, String description, String location) {
        this.title = title;
        this.description = description;
        this.location = location;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return getId().equals(job.getId()) &&
                getTitle().equals(job.getTitle()) &&
                getDescription().equals(job.getDescription()) &&
                getLocation().equals(job.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getTitle(),
                getDescription(),
                getLocation());
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
