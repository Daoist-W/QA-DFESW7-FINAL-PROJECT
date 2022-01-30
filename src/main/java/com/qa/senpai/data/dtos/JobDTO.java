package com.qa.senpai.data.dtos;

import java.time.LocalDate;
import java.util.List;

public class JobDTO {

    Long id;
    String title;
    String description;
    String location;
    List<LocalDate> dates;

    public JobDTO(Long id,
                  String title,
                  String description,
                  String location,
                  List<LocalDate> dates) {
        this.id = id;
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

    @Override
    public String toString() {
        return "JobDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", dates=" + dates +
                '}';
    }
}
