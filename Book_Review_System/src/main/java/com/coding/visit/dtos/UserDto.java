package com.coding.visit.dtos;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
    String userId;
    @NotBlank(message = "name can not empty and null")
    @Size(min = 3, max = 100,message = "name character between  3 and 100")
    String name;

    @Pattern(
            regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
            message = "Invalid Email"
    )
//    @Email(message = "invalid email ")
    String email ;

    @Size(min = 5, max = 50,message = "password character between  5 and 50")
    @NotBlank(message = "password can not empty and null")
    String password;

    @Size(min = 1, max = 2000, message = "name character between 1 to 2000")
    String about;

    @NotBlank(message = "gender can not empty and null")
    @Size(min= 4 ,  max = 6,message = "invalid gender")
    String gender ;

    public @Pattern(
            regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
            message = "Invalid Email"
    ) String getEmail() {
        return email;
    }

    public void setEmail(@Pattern(
            regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
            message = "Invalid Email"
    ) String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public @NotBlank(message = "name can not empty and null") @Size(min = 3, max = 100, message = "name character between  3 and 100") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name can not empty and null") @Size(min = 3, max = 100, message = "name character between  3 and 100") String name) {
        this.name = name;
    }


    public @Size(min = 5, max = 50, message = "password character between  5 and 50") @NotBlank(message = "password can not empty and null") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 5, max = 50, message = "password character between  5 and 50") @NotBlank(message = "password can not empty and null") String password) {
        this.password = password;
    }

    public @Size(min = 1, max = 2000, message = "name character between 1 to 2000") String getAbout() {
        return about;
    }

    public void setAbout(@Size(min = 1, max = 2000, message = "name character between 1 to 2000") String about) {
        this.about = about;
    }

    public @NotBlank(message = "gender can not empty and null") @Size(min = 4, max = 6, message = "invalid gender") String getGender() {
        return gender;
    }

    public void setGender(@NotBlank(message = "gender can not empty and null") @Size(min = 4, max = 6, message = "invalid gender") String gender) {
        this.gender = gender;
    }
}
