package com.miu.flightmanagement.authorizationservice.util;

import com.miu.flightmanagement.authorizationservice.persistence.model.User;
import com.miu.flightmanagement.authorizationservice.web.dto.UserDto;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.util.Optional;

@UtilityClass
public class UserUtils {

    @Nullable
    public static User toUser(@Nullable final UserDto userDTO) {
        return Optional.ofNullable(userDTO)
                .map(dto -> new User(
                        null,
                        userDTO.getFirstName(),
                        userDTO.getLastName(),
                        userDTO.getEmail(),
                        userDTO.getPassword(),
                        userDTO.getPhone(),
                        null
        )).orElse(null);
    }

    @Nullable
    public static UserDto toUserDTO(@Nullable final User user) {
        return Optional.ofNullable(user)
                .map(u -> UserDto.builder()
                        .firstName(u.getFirstName())
                        .lastName(u.getLastName())
                        .email(u.getEmail())
                        .phone(u.getPhone())
                        .build())
                .orElse(null);
    }

}
