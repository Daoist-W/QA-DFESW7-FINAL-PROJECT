package com.qa.senpai.data.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Job {
    // Fields
    Long id;
    String title;
    String description;
    String location;
    List<LocalDate> dates;
    User user;

    public Job(Long id, String title, String description, String location, List<LocalDate> dates) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.dates = dates;
    }

    public Job(String title, String description, String location, List<LocalDate> dates) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.dates = dates;
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

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
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
        return getId().equals(job.getId()) &&
                getTitle().equals(job.getTitle()) &&
                getDescription().equals(job.getDescription()) &&
                getLocation().equals(job.getLocation()) &&
                Objects.equals(getDates(), job.getDates()) &&
                Objects.equals(getUser(), job.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getTitle(),
                getDescription(),
                getLocation(),
                getDates(),
                getUser());
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", dates=" + dates +
                ", user=" + user +
                '}';
    }
}
