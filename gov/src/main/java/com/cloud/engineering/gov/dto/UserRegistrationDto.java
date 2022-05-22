package com.cloud.engineering.gov.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto{
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Name must be between 4 and 20 characters")
    private String userName;
    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Sorry, but the given password is too short. Passwords must be at least 6 characters long.")
    private String password;
    @Email(message = "Please enter a valid email")
    @NotEmpty
    private String email;

    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}

