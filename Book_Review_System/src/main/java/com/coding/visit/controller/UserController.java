package com.coding.visit.controller;

import com.coding.visit.dtos.ApiResponseMessage;
import com.coding.visit.dtos.UserDto;
import com.coding.visit.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService ;

    //sign up or new user
    @PostMapping
    public ResponseEntity<UserDto > signUpUser(@Valid @RequestBody UserDto userDto){
        return  new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED) ;
    }

    //get user id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable  String userId){
        return  new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK) ;
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId) ;
        ApiResponseMessage message = new ApiResponseMessage() ;
        message.setMessage("successfully deleted" );
        message.setStatus(HttpStatus.OK);
        message.setSuccess(true) ;

        return ResponseEntity.ok(message) ;
    }


    //get all user
    @GetMapping
    public ResponseEntity<Page<UserDto>>  getAllUser(
            @RequestParam(value = "pageNumber" ,defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize" ,defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy" ,defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir" ,defaultValue = "asc", required = false) String sortDir


            )
    {
        return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK) ;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId , @Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(userId,userDto),HttpStatus.OK) ;
    }

    //get user by email

}
