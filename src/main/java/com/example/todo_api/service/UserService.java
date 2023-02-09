package com.example.todo_api.service;

import com.example.todo_api.model.dto.UserDto;

import java.util.Map;

public interface UserService {

    UserDto register(UserDto dto);

     Map<String, Object> findByUsername(String username);

     void disableUserByUsername(String username);
}
