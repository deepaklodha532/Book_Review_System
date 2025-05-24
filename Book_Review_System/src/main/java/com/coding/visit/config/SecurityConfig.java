package com.coding.visit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(i->i.disable());
        httpSecurity.authorizeHttpRequests(request->
                request.requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/books/**").hasRole("ADMIN")
                        .requestMatchers("books/**").permitAll()
                        .requestMatchers("/reviews/**").hasAnyRole("NORMAL","ADMIN")
                        .anyRequest().authenticated()
                );

        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build() ;
    }

}
