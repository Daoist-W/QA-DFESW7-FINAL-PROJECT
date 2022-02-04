package com.qa.senpai.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qa.senpai.data.support.Position;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Users")
public class User {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Position position_;

    @NotNull
    @Length(min = 1, max = 50 ,message = "Names cannot be empty")
    private String forename;

    @NotNull
    @Length(min = 1, max = 50, message = "Names cannot be empty")
    private String surname;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;

    @NotNull
    // TODO: implement constraint for email format
    private String email;

    @NotNull
    // TODO: implement constraint for phone numbers
    private String phoneNum;

    @NotNull
    private int passcode;

    // @OneToMany signifies that a User can have many Cars
    // this object is mapped to 'user' variable in Jobs by hibernate/JPA
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Job> jobs;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Availability> availabilities;

    public User(){

    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id,
                Position position_,
                String forename,
                String surname,
                LocalDate dob,
                String email,
                String phoneNum,
                int passcode,
                List<Job> jobs,
                List<Availability> availabilities) {
        this.id = id;
        this.position_ = position_;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.phoneNum = phoneNum;
        this.passcode = passcode;
        this.jobs = jobs;
        this.availabilities = availabilities;
    }

    public User(Position position_,
                String forename,
                String surname,
                LocalDate dob,
                String email,
                String phoneNum,
                int passcode,
                List<Job> jobs,
                List<Availability> availabilities) {
        this.position_ = position_;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.phoneNum = phoneNum;
        this.passcode = passcode;
        this.jobs = jobs;
        this.availabilities = availabilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition_() {
        return position_;
    }

    public void setPosition_(Position position_) {
        this.position_ = position_;
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

    public int getPasscode() {
        return passcode;
    }

    public void setPasscode(int passcode) {
        this.passcode = passcode;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getPasscode() == user.getPasscode() &&
                getId().equals(user.getId()) &&
                getPosition_() == user.getPosition_() &&
                getForename().equals(user.getForename()) &&
                getSurname().equals(user.getSurname()) &&
                getDob().equals(user.getDob()) &&
                getEmail().equals(user.getEmail()) &&
                getPhoneNum().equals(user.getPhoneNum()) &&
                Objects.equals(getJobs(), user.getJobs()) &&
                Objects.equals(getAvailabilities(), user.getAvailabilities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPosition_(),
                getForename(), getSurname(), getDob(),
                getEmail(), getPhoneNum(),
                getPasscode(),
                getJobs(),
                getAvailabilities());
    }
}
