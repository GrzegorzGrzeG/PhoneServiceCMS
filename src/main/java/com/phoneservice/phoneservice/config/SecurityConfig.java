package com.phoneservice.phoneservice.config;


import com.phoneservice.phoneservice.service.CustomUserDetailsService;
import com.phoneservice.phoneservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        http.authorizeHttpRequests(authorization ->
                        authorization
                                .shouldFilterAllDispatcherTypes(false)
                                .requestMatchers(HttpMethod.GET, "/").permitAll()
                                .requestMatchers(HttpMethod.POST, "/").permitAll()
                                .requestMatchers(HttpMethod.GET, "/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/client/**").hasAuthority("CLIENT")
                                .requestMatchers(HttpMethod.POST, "/client/**").hasAuthority("CLIENT")
                                .requestMatchers(HttpMethod.GET, "/tech/**").hasAuthority("TECH")
                                .requestMatchers(HttpMethod.POST, "/tech/**").hasAuthority("TECH")
                                .anyRequest()
                                .authenticated())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}