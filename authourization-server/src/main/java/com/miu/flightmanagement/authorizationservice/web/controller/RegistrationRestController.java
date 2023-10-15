package com.miu.flightmanagement.authorizationservice.web.controller;

import com.miu.flightmanagement.authorizationservice.persistence.model.User;
import com.miu.flightmanagement.authorizationservice.service.UserService;
import com.miu.flightmanagement.authorizationservice.web.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin
public class RegistrationRestController {
    private final UserService userService;

    @PostMapping("/users/registration")
    public ResponseEntity<?> registerUserAccount(@RequestBody final UserDto accountDto) {
        log.debug("Registering user account with information: {}", accountDto);

        final User registered = userService.registerNewUserAccount(accountDto);
        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }
}
