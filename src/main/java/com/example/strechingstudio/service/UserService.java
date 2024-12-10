package com.example.strechingstudio.service;

import com.example.strechingstudio.dto.UserDto;
import com.example.strechingstudio.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}