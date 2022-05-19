package com.cloud.engineering.citizen.service;

import com.cloud.engineering.citizen.dto.UserRegistrationDto;
import com.cloud.engineering.citizen.model.UserModel;
import com.cloud.engineering.citizen.repository.GovRepository;
import com.cloud.engineering.citizen.utils.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GovService {
    @Autowired
    private GovRepository govRepository;

    public boolean registerCitizen(UserRegistrationDto request) {
        log.info("Service - Register user");
        UserModel user = govRepository.findByEmail(request.getEmail());

        if(user != null) return false;

        UserModel newUser = UserModel.builder()
                    .userName(request.getUserName())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .nameOfMother(request.getNameOfMother())
                    .nameOfFather(request.getNameOfFather())
                    .age(request.getAge().intValue())
                    .nationality(request.getNationality())
                    .build();

        govRepository.save(newUser);
        return true;
    }

    public UserModel findUserByEmail(String email) throws UserNotFoundException {
        log.info("Service - Find User By Email");
        UserModel user = govRepository.findByEmail(email);
        return user;
    }

    public UserModel findUserById(Long id) throws UserNotFoundException {
        log.info("Service - Find User By Id");
        Optional<UserModel> user = govRepository.findById(id);
        return user.get();
    }
}
