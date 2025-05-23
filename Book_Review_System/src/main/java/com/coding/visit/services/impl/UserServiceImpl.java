package com.coding.visit.services.impl;

import com.coding.visit.dtos.UserDto;
import com.coding.visit.entities.User;
import com.coding.visit.exceptions.ResourceNotFoundException;
import com.coding.visit.repositories.UserRepository;
import com.coding.visit.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        User saved = userRepository.save(mapper.map(userDto , User.class));
        return mapper.map(saved, UserDto.class ) ;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found given id "));
        userRepository.delete(user) ;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found given id "));
        return mapper.map(user, UserDto.class) ;
    }

    @Override
    public Page<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize , Sort.by(sortBy).ascending());
        Page<User>page = userRepository.findAll(pageable);
        List<UserDto> userDtos =  page.stream().map(user -> mapper.map(user,UserDto.class)).collect(Collectors.toList());
        return new PageImpl<>(userDtos, pageable,page.getTotalElements());
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found given id "));
        user.setAbout(userDto.getAbout());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        User updated= userRepository.save(user );
        return mapper.map(updated, UserDto.class) ;
    }
}
