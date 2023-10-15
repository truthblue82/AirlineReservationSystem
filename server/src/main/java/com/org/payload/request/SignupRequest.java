package com.org.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @JsonProperty("username")
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    @JsonProperty("email")
    private String email;

    @JsonProperty("roles")
    private Set<String> roles;
    
    @NotBlank
    @Size(min = 6, max = 40)
    @JsonProperty("password")
    private String password;

    @NotBlank
    @JsonProperty("fullName")
    private String fullName;

    @NotBlank
    @JsonProperty("phone")
    private String phone;
}