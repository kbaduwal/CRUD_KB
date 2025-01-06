package com.example.crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**","/api/books/**","/swagger-ui/**",
                                "/v3/api-docs/**").permitAll()  // Public endpoints
                        .anyRequest().authenticated()  // Secure other endpoints
                )
                .httpBasic(httpBasic -> httpBasic.disable());  // Enable Basic Authentication properly

        return http.build();
    }
}


