package com.example.blogapp.service.implementations;

import com.example.blogapp.exceptions.ResourceNotFoundException;
import com.example.blogapp.models.User;
import com.example.blogapp.payloads.UserDto;
import com.example.blogapp.repositories.UserRepository;
import com.example.blogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }






    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToModel(userDto);
        User savedUser = userRepository.save(user);
        return modelToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepository.save(user);

        return modelToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));

        return modelToDto(user);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
        userRepository.deleteById(userId);
    }

    private User dtoToModel(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    private UserDto modelToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
