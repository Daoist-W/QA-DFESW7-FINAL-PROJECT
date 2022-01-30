package com.qa.senpai.data.dtos;

import com.qa.senpai.data.entities.Job;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {
    // Fields
    Long id;
    String position;
    String forename;
    String surname;
    String email;
    String phoneNum;
    List<LocalDate> availability;
    List<Job> jobList;

    public UserDTO(Long id,
                   String position,
                   String forename,
                   String surname, String email,
                   String phoneNum,
                   List<LocalDate> availability,
                   List<Job> jobList) {
        this.id = id;
        this.position = position;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.phoneNum = phoneNum;
        this.availability = availability;
        this.jobList = jobList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<LocalDate> getAvailability() {
        return availability;
    }

    public void setAvailability(List<LocalDate> availability) {
        this.availability = availability;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", availability=" + availability +
                ", jobList=" + jobList +
                '}';
    }
}
