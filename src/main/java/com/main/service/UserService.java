package com.main.service;

import com.main.entity.User;
import com.main.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUser();
    void deleteUser(Long userId);

}
