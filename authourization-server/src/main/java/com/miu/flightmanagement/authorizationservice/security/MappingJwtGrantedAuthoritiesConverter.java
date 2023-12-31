package com.miu.flightmanagement.authorizationservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class MappingJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final Collection<String> WELL_KNOWN_AUTHORITIES_CLAIM_NAMES = Arrays.asList("scope", "scp");

    private String authoritiesClaimName = null;
    private String authorityPrefix = "SCOPE_";

    public void setAuthoritiesClaimName(String authoritiesClaimName) {
        this.authoritiesClaimName = authoritiesClaimName;
    }

    public void setAuthorityPrefix(String authorityPrefix) {
        this.authorityPrefix = authorityPrefix;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Collection<String> tokenScopes = parseScopesClaim(jwt);
        if ( tokenScopes.isEmpty()) {
            return Collections.emptyList();
        }

        return tokenScopes.stream()
                .map(s -> this.authorityPrefix + s)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toCollection(HashSet::new));
    }

    protected Collection<String> parseScopesClaim(Jwt jwt) {

        String scopeClaim;

        if ( this.authoritiesClaimName == null ) {
            scopeClaim = WELL_KNOWN_AUTHORITIES_CLAIM_NAMES.stream()
                    .filter( claim -> jwt.hasClaim(claim))
                    .findFirst()
                    .orElse(null);

            if ( scopeClaim == null ) {
                return Collections.emptyList();
            }
        }
        else {
            scopeClaim = this.authoritiesClaimName;
        }

        Object v = jwt.getClaim(scopeClaim);
        if ( v == null ) {
            return Collections.emptyList();
        }

        if ( v instanceof String) {
            return Arrays.asList(v.toString().split(" "));
        }
        else if ( v instanceof Collection ) {
            return ((Collection<?>)v).stream()
                    .map( s -> s.toString())
                    .collect(Collectors.toCollection(HashSet::new));
        }
        return Collections.emptyList();
    }
}
