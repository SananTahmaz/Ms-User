package com.devconnect.app.services;

import com.devconnect.app.dtos.user.UserDto;
import com.devconnect.app.dtos.user.UserRegisterDto;
import com.devconnect.app.dtos.user.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto register(UserRegisterDto registerDto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto update(Long id, UserUpdateDto updateDto);
    void delete(Long id);
}
