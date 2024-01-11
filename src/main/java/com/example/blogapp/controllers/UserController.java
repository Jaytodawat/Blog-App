package com.example.blogapp.controllers;

import com.example.blogapp.payloads.ApiResponse;
import com.example.blogapp.payloads.UserDto;
import com.example.blogapp.service.implementations.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }


    @PutMapping("/{uid}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer uid){

        UserDto updatedUserDto = userService.updateUser(userDto, uid);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }


    @DeleteMapping("/{uid}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer uid){
        userService.deleteUser(uid);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }


    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtoList = userService.getAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }


}
