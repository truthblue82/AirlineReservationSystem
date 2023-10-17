package com.miu.flightmanagement.authorizationservice.service;

import com.miu.flightmanagement.authorizationservice.web.dto.UserDto;
import com.miu.flightmanagement.authorizationservice.persistence.model.User;

import java.util.Optional;

public interface UserService {
    User registerNewUserAccount(UserDto accountDto);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    User findUserByEmail(String email);

    Optional<User> getUserByID(long id);


    UserDto findUserByPrincipal(String principalClaimValue);
}
