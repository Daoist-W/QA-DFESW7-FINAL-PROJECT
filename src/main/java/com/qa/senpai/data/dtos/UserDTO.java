package com.qa.senpai.data.dtos;

import com.qa.senpai.data.support.Position;

import java.time.LocalDate;
import java.util.Objects;

public class UserDTO {
    // Fields
    Long id;
    Position position_;
    String forename;
    String surname;
    LocalDate dob;
    String email;
    String phoneNum;

    public UserDTO() {
    }

    public UserDTO(Long id,
                   Position position_,
                   String forename,
                   String surname,
                   LocalDate dob,
                   String email,
                   String phoneNum) {
        this.id = id;
        this.position_ = position_;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.phoneNum = phoneNum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return getId().equals(userDTO.getId()) && getPosition_() == userDTO.getPosition_() && getForename().equals(userDTO.getForename()) && getSurname().equals(userDTO.getSurname()) && getDob().equals(userDTO.getDob()) && getEmail().equals(userDTO.getEmail()) && getPhoneNum().equals(userDTO.getPhoneNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPosition_(), getForename(), getSurname(), getDob(), getEmail(), getPhoneNum());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", position_=" + position_ +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
