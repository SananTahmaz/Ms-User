package com.devconnect.app.mappers;

import com.devconnect.app.dtos.user.UserDto;
import com.devconnect.app.dtos.user.UserRegisterDto;
import com.devconnect.app.dtos.user.UserUpdateDto;
import com.devconnect.app.entities.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User toEntity(UserRegisterDto registerDto) {
        return modelMapper.map(registerDto, User.class);
    }

    public void updateEntity(UserUpdateDto updateDto, User user) {
        modelMapper.map(updateDto, user);
    }
}
