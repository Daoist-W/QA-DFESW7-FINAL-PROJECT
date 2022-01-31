package com.qa.senpai.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qa.senpai.data.support.Position;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    private Position position_; // TODO: look into this implementation

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

    // TODO: implement entity relations with Dates and Jobs
    // TODO: create a entity/repo/service/controller suite for dates
//    List<LocalDate> availability;
//    List<Job> jobList;

    public User(){

    }

    public User(Position position_,
                String forename,
                String surname,
                LocalDate dob,
                String email,
                String phoneNum,
                String passcode) {
        this.position_ = position_;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.phoneNum = phoneNum;
        // TODO: Review hash method, is there a better algorithm we can use?
        this.passcode = passcode.hashCode();
    }

    public User(Long id,
                Position position_,
                String forename,
                String surname,
                LocalDate dob,
                String email,
                String phoneNum,
                String passcode) {
        this.id = id;
        this.position_ = position_;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.phoneNum = phoneNum;
        // TODO: Review hash method, is there a better algorithm we can use?
        this.passcode = passcode.hashCode();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getPasscode() == user.getPasscode() &&
                getId().equals(user.getId()) &&
                getPosition_().equals(user.getPosition_()) &&
                getForename().equals(user.getForename()) &&
                getSurname().equals(user.getSurname()) &&
                getDob().equals(user.getDob()) &&
                getEmail().equals(user.getEmail()) &&
                getPhoneNum().equals(user.getPhoneNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getPosition_(),
                getForename(),
                getSurname(),
                getDob(),
                getEmail(),
                getPhoneNum(),
                getPasscode());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", position_='" + position_ + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
