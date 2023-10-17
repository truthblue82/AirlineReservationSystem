package com.miu.flightmanagement.authorizationservice.security.model;

import com.miu.flightmanagement.authorizationservice.web.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class AccountToken extends JwtAuthenticationToken {

    private final UserDto user;

    public AccountToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String name, UserDto user) {
        super(jwt, authorities, name);
        this.user = user;
    }

    public UserDto getUser() {
        return this.user;
    }


}
