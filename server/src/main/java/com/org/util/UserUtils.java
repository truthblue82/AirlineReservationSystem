package com.org.util;

import com.org.dto.UserDTO;
import com.org.model.User;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.util.Optional;

@UtilityClass
public class UserUtils {

    @Nullable
    public static User toUser(@Nullable final UserDTO userDTO) {
        return Optional.ofNullable(userDTO)
                .map(dto -> new User(
                        userDTO.getId(),
                        userDTO.getUsername(),
                        userDTO.getEmail(),
                        userDTO.getPassword()
        )).orElse(null);
    }

    @Nullable
    public static UserDTO toUserDTO(@Nullable final User user) {
        return Optional.ofNullable(user)
                .map(u -> UserDTO.builder()
                        .id(u.getId())
                        .username(u.getUsername())
                        .email(u.getEmail())
                        .fullName(u.getFullname())
                        .phone(u.getPhone())
                        .build())
                .orElse(null);
    }

}
