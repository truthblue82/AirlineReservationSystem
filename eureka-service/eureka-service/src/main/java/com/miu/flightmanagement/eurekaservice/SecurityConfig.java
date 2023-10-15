package com.miu.flightmanagement.eurekaservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final String username;
    private final String password;

    public SecurityConfig(
            @Value("${app.eureka-username}") final String username,
            @Value("${app.eureka-password}") final String password) {
        this.username = username;
        this.password = password;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final UserDetails user = User.withDefaultPasswordEncoder()
                .username(this.username)
                .password(this.password)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                    .anyRequest().authenticated()
                        .and()
                        .httpBasic(Customizer.withDefaults());

         return http.build();
    }
}
