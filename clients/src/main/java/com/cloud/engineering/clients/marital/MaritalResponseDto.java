package com.cloud.engineering.clients.marital;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaritalResponseDto {
    private String userName;

    private String email;

    private String firstName;
    private String lastName;
    private String nationality;
    private String city;
    public MaritalResponseDto(){}
    public MaritalResponseDto(String userName, String email, String firstName, String lastName, String nationality, String city) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
