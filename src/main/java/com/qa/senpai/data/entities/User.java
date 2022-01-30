package com.qa.senpai.data.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class User {
    // Fields
    Long id;
    String position;
    String forename;
    String surname;
    LocalDate dob;
    String email;
    String phoneNum;
    int password;
    List<LocalDate> availability;
    List<Job> jobList;

    public User(Long id,
                String position,
                String forename,
                String surname,
                LocalDate dob,
                String email,
                String phoneNum,
                String password) {
        this.id = id;
        this.position = position;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.phoneNum = phoneNum;
        // TODO: Review hash method, is there a better algorithm we can use?
        this.password = password.hashCode();
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getPassword() == user.getPassword() &&
                getId().equals(user.getId()) &&
                getPosition().equals(user.getPosition()) &&
                getForename().equals(user.getForename()) &&
                getSurname().equals(user.getSurname()) &&
                getDob().equals(user.getDob()) &&
                getEmail().equals(user.getEmail()) &&
                getPhoneNum().equals(user.getPhoneNum()) &&
                Objects.equals(getAvailability(), user.getAvailability()) &&
                Objects.equals(getJobList(), user.getJobList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getPosition(),
                getForename(),
                getSurname(),
                getDob(),
                getEmail(),
                getPhoneNum(),
                getPassword(),
                getAvailability(),
                getJobList());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", availability=" + availability +
                ", jobList=" + jobList +
                '}';
    }
}
