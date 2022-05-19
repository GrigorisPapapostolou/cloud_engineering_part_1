package com.cloud.engineering.citizen.repository;

import com.cloud.engineering.citizen.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GovRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);

}
