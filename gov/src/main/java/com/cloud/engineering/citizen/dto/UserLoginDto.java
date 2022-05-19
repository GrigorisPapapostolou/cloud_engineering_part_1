package com.cloud.engineering.citizen.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginDto {
    @Email(message = "Please enter a valid email")
    @NotEmpty
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Sorry, but the given password is too short. Passwords must be at least 6 characters long.")
    private String password;

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



