package com.devconnect.app.services;

import com.devconnect.app.dtos.user.UserDto;
import com.devconnect.app.dtos.user.UserRegisterDto;
import com.devconnect.app.dtos.user.UserSearchDto;
import com.devconnect.app.dtos.user.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    UserDto register(UserRegisterDto registerDto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    List<UserDto> search(UserSearchDto searchDto, Sort sort);
    Page<UserDto> search(UserSearchDto searchDto, Pageable pageable);
    UserDto update(Long id, UserUpdateDto updateDto);
    void delete(Long id);
}
