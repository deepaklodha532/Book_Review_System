package com.coding.visit.config;

import com.coding.visit.security.JwtAuthenticationEntryPoint;
import com.coding.visit.security.JwtAuthenticationFilter;
import com.coding.visit.services.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Autowired
    private JwtAuthenticationFilter filter ;

    @Autowired
    private UserDetailsService userDetailsService ;

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(this.userDetailsService) ;
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(i->i.disable());
        httpSecurity.authorizeHttpRequests(request->
                request
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("ADMIN","NORMAL")
                        .requestMatchers(HttpMethod.POST,"/books/**").hasRole("ADMIN")
                        .requestMatchers("books/**").hasAnyRole("NORMAL","ADMIN")
                        .requestMatchers("/reviews/**").hasAnyRole("NORMAL","ADMIN")
                        .anyRequest().authenticated()
                );


        httpSecurity.exceptionHandling(e->e.authenticationEntryPoint(entryPoint))   ;
        httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) ;
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) ;

//        httpSecurity.httpBasic(Customizer.withDefaults());
//        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build() ;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration object) throws Exception {
        return object.getAuthenticationManager();
    }
}
