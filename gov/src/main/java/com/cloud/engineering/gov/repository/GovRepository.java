package com.cloud.engineering.gov.repository;

import com.cloud.engineering.gov.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GovRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);

}
