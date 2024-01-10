package com.example.blogapp.service;

import com.example.blogapp.models.User;
import com.example.blogapp.payloads.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

}
