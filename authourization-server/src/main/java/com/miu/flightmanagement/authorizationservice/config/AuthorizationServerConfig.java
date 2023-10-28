package com.miu.flightmanagement.authorizationservice.config;

import com.miu.flightmanagement.authorizationservice.jose.Jwks;
import com.miu.flightmanagement.authorizationservice.security.CustomJwtAuthenticationConverter;
import com.miu.flightmanagement.authorizationservice.security.MappingJwtGrantedAuthoritiesConverter;
import com.miu.flightmanagement.authorizationservice.service.UserService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.authentication.*;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Configuration(proxyBeanMethods = false)
@Slf4j
@AllArgsConstructor
@EnableMethodSecurity
public class AuthorizationServerConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityChain(final HttpSecurity http, @Qualifier("customJwtAuthenticationConverter") final Converter<Jwt, ? extends AbstractAuthenticationToken> jwtConverter) throws Exception {
        final OAuth2AuthorizationServerConfigurer oAuth2AuthorizationConfigurer =
                new OAuth2AuthorizationServerConfigurer();

        oAuth2AuthorizationConfigurer.authorizationEndpoint(endpoint ->
                endpoint.authenticationProviders(configureAuthenticationProviders())
        );

        final RequestMatcher endpointsMatcher = oAuth2AuthorizationConfigurer.getEndpointsMatcher();
        http
                .securityMatcher(endpointsMatcher)
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().authenticated()
                ).csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .apply(oAuth2AuthorizationConfigurer);


        // Enable OpenId Connect 1.0 (OIDC 1.0)
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());


        http
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                .oauth2ResourceServer(oAuth2ResourceServerConfigurer ->
                        oAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(@Value("${app.security.redirect-url}") final String redirectUrl,
                                                                 final PasswordEncoder passwordEncoder) {
        RegisteredClient writerClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("writer")
                .clientSecret(passwordEncoder.encode("secret-writer"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri(redirectUrl)
                .scope(OidcScopes.OPENID)
                .scope("product:read")
                .scope("product:write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(3)).build())
                .build();

        RegisteredClient readerClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("reader")
                .clientSecret(passwordEncoder.encode("secret-reader"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri(redirectUrl)
                .scope(OidcScopes.OPENID)
                .scope("product:read")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(3)).build())
                .build();

        return new InMemoryRegisteredClientRepository(writerClient, readerClient);

    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings(
            @Value("${app.host-name}") final String hostName,
            @Value("${server.port}") final String serverPort
    ) {
        final String url = "http://" + hostName + ":" + serverPort;
        return AuthorizationServerSettings.builder().issuer(url).build();
    }

    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        return new MappingJwtGrantedAuthoritiesConverter();
    }

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> customJwtAuthenticationConverter(UserService accountService,
                                                                                        @Qualifier("jwtGrantedAuthoritiesConverter") final Converter<Jwt, Collection<GrantedAuthority>> jwtAuthoritiesConverter ) {
        return new CustomJwtAuthenticationConverter(
                accountService,
                jwtAuthoritiesConverter);
    }

    private Consumer<List<AuthenticationProvider>> configureAuthenticationProviders() {
        return (authenticationProviders -> authenticationProviders.stream()
                .filter(OAuth2AuthorizationCodeRequestAuthenticationProvider.class::isInstance)
                .map(OAuth2AuthorizationCodeRequestAuthenticationProvider.class::cast)
                .forEach(oAuth2AuthorizationCodeRequestAuthenticationProvider -> {
                    Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> authenticationValidator =
                            // Override default redirect_uri validator
                            new CustomRedirectUriValidator()
                                    // Reuse default scope validator
                                    .andThen(OAuth2AuthorizationCodeRequestAuthenticationValidator.DEFAULT_SCOPE_VALIDATOR);
                    oAuth2AuthorizationCodeRequestAuthenticationProvider.setAuthenticationValidator(authenticationValidator);
                }));
    }

    static class CustomRedirectUriValidator implements Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> {

        @Override
        public void accept(OAuth2AuthorizationCodeRequestAuthenticationContext authenticationContext) {
            OAuth2AuthorizationCodeRequestAuthenticationToken authorizationCodeRequestAuthentication =
                    authenticationContext.getAuthentication();
            RegisteredClient registeredClient = authenticationContext.getRegisteredClient();
            String requestedRedirectUri = authorizationCodeRequestAuthentication.getRedirectUri();

            log.trace("Will validate the redirect uri {}", requestedRedirectUri);

            // Use exact string matching when comparing client redirect URIs against pre-registered URIs
            if (!registeredClient.getRedirectUris().contains(requestedRedirectUri)) {
                log.trace("Redirect uri is invalid!");
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST);
                throw new OAuth2AuthorizationCodeRequestAuthenticationException(error, null);
            }
            log.trace("Redirect uri is OK!");
        }
    }
}
