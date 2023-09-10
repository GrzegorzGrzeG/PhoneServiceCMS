package com.phoneservice.phoneservice.config;


import com.phoneservice.phoneservice.service.CustomUserDetailsService;
import com.phoneservice.phoneservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(request -> "/".equals(request.getRequestURI()) || "/register".equals(request.getRequestURI())).permitAll()
                        .requestMatchers(request -> "/admin".equals(request.getRequestURI())).hasRole("ADMIN")
                        .requestMatchers(request -> "/client".equals(request.getRequestURI())).hasRole("CLIENT")
                        .requestMatchers(request -> "/tech".equals(request.getRequestURI())).hasRole("TECH")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .userDetailsService(customUserDetailsService);

        return http.build();
    }
}