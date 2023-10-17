package com.miu.flightmanagement.authorizationservice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UserDto {
    private String firstName;

    private String lastName;

    private String fullName;

    private String password;

    private String email;

    private String phone;

}
