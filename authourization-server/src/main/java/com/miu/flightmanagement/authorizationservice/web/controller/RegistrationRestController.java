package com.miu.flightmanagement.authorizationservice.web.controller;

import com.miu.flightmanagement.authorizationservice.service.UserService;
import com.miu.flightmanagement.authorizationservice.web.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin
public class RegistrationRestController {
    private final UserService userService;

    @PostMapping("/users/registration")
    public ResponseEntity<?> registerUserAccount(@RequestBody final UserDto accountDto) {
        log.debug("Registering user account with information: {}", accountDto);

        userService.registerNewUserAccount(accountDto);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping("/users/info")
    public ResponseEntity<?> currentInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken authJwt) {
            return ResponseEntity.ok(this.userService.findUserByPrincipal(authJwt.getName()));
        }
        return ResponseEntity.notFound().build();
    }
}
