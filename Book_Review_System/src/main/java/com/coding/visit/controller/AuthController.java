package com.coding.visit.controller;

import com.coding.visit.dtos.JwtRequest;
import com.coding.visit.dtos.JwtResponse;
import com.coding.visit.dtos.UserDto;
import com.coding.visit.security.JwtUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager ;

    @Autowired
    private JwtUtil jwtUtil ;

    @Autowired
    ModelMapper mapper ;

    @Autowired
    UserDetailsService userDetailsService ;

    Logger logger = LoggerFactory.getLogger(AuthController.class) ;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        doAuthenticate(request.getUsername(),request.getPassword()) ;
        UserDetails details = userDetailsService.loadUserByUsername(request.getUsername()) ;
        String token =jwtUtil.generateToken(details) ;
        logger.info("token generated : {}",token) ;
        UserDto userDto  = mapper.map(details ,UserDto.class) ;

        JwtResponse jwtResponse = new JwtResponse() ;
        jwtResponse.setToken(token);
        jwtResponse.setUserDto(userDto);
        logger.info("jwt response {}",jwtResponse) ;

        return new ResponseEntity<>(jwtResponse, HttpStatus.CREATED) ;

    }

    public void doAuthenticate(String email ,String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password) ;
        try{
            manager.authenticate(authentication) ;
        }catch (BadCredentialsException e){
            logger.error(" do authenticated time exception  : {}",e.getMessage()); ;
            System.out.println(e.getMessage()) ;
        }
    }
}
