package com.coding.visit.repositories;

import com.coding.visit.dtos.UserDto;
import com.coding.visit.entities.User;
import org.apache.catalina.UserDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email) ;
}
