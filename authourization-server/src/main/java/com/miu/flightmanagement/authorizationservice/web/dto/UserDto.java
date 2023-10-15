package com.miu.flightmanagement.authorizationservice.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDto {
    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phone;

}
