package com.coding.visit.services;

import com.coding.visit.dtos.UserDto;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface UserService {
    //create
    UserDto createUser(UserDto userDto);

    //delete user
    void deleteUser(String userId);

    //get user by id
    UserDto getUserById(String userId);

    //get all user
    Page<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy , String sortDir);

    //update user
    UserDto updateUser(String userId, UserDto userDto);



}
