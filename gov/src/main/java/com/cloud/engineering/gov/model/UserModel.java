package com.cloud.engineering.gov.model;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserModel {
    @Id
    @SequenceGenerator(name="user_id_sequence", sequenceName = "user_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Long id;
    @Column(length = 50)
    private String userName;
    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private String email;
    @Column(length = 50)

    private String firstName;
    @Column(length = 50)

    private String lastName;

    public UserModel(){}

    public UserModel(Long id, String userName, String password, String email, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
