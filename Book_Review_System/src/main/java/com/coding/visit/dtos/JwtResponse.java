package com.coding.visit.dtos;

import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtResponse {
    String token;
    UserDto userDto ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
