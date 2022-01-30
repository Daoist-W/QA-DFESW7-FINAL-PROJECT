package com.qa.senpai.data.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String position; // enum

    @NotNull
    @Length(min = 1, message = "Names cannot be empty")
    String forename;

    @NotNull
    @Length(min = 1, message = "Names cannot be empty")
    String surname;

    @NotNull
    LocalDate dob;

    @NotNull
    // TODO: implement constraint for email format
    String email;

    @NotNull
    // TODO: implement constraint for phone numbers
    String phoneNum;

    @NotNull
    int password;

    // TODO: implement entity relations with Dates and Jobs
    // TODO: create a entity/repo/service/controller suite for dates
//    List<LocalDate> availability;
//    List<Job> jobList;

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
                getPhoneNum().equals(user.getPhoneNum());
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
                getPassword());
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
                '}';
    }
}
