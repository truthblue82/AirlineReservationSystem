package com.miu.flightmanagement.authorizationservice.security;

import com.miu.flightmanagement.authorizationservice.persistence.model.User;
import com.miu.flightmanagement.authorizationservice.security.model.AccountToken;
import com.miu.flightmanagement.authorizationservice.service.UserService;
import com.miu.flightmanagement.authorizationservice.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;

import java.util.Collection;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter;
    private final String principalClaimName;
    private UserService userService;

    public CustomJwtAuthenticationConverter(UserService accountService, Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
        this.userService = accountService;
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
        this.principalClaimName =  JwtClaimNames.SUB;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {

        Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(source);
        String principalClaimValue = source.getClaimAsString(this.principalClaimName);
        UserDto acc = userService.findUserByPrincipal(principalClaimValue);
        return new AccountToken(source, authorities, principalClaimValue, acc);
    }
}
