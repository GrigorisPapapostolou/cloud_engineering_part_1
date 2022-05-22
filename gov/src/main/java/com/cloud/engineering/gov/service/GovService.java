package com.cloud.engineering.gov.service;

import com.cloud.engineering.clients.marital.UserDto;
import com.cloud.engineering.gov.dto.UserRegistrationDto;
import com.cloud.engineering.gov.model.UserModel;
import com.cloud.engineering.gov.repository.GovRepository;
import com.cloud.engineering.gov.utils.UserNotFoundException;
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
                    .build();

        govRepository.save(newUser);
        return true;
    }

    public UserDto findUserByEmail(String email) throws UserNotFoundException {
        log.info("Service - Find User By Email");
        UserModel u = govRepository.findByEmail(email);
        return UserDto.builder()
                .id(u.getId())
                .userName(u.getUserName())
                .email(u.getEmail())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .build();
    }

    public UserDto findUserById(Long id) throws UserNotFoundException {
        log.info("Service - Find User By Id");
        Optional<UserModel> user = govRepository.findById(id);
        UserModel u = user.get();
        return UserDto.builder()
                .id(u.getId())
                .userName(u.getUserName())
                .email(u.getEmail())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .build();
    }
}
