package com.devconnect.app.services.implementations;

import com.devconnect.app.dtos.user.UserDto;
import com.devconnect.app.dtos.user.UserRegisterDto;
import com.devconnect.app.dtos.user.UserUpdateDto;
import com.devconnect.app.entities.User;
import com.devconnect.app.exceptions.AlreadyExistsException;
import com.devconnect.app.exceptions.NotFoundException;
import com.devconnect.app.mappers.UserMapper;
import com.devconnect.app.repositories.UserRepository;
import com.devconnect.app.services.UserService;
import com.devconnect.app.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto register(UserRegisterDto registerDto) {
        boolean isExistByUsername = userRepository.existsByUsernameIgnoreCase(
                registerDto.getUsername().toLowerCase(Locale.ROOT).trim()
        );

        if (isExistByUsername) {
            throw new AlreadyExistsException(
                    String.format("User already exists with username: %s", registerDto.getUsername())
            );
        }

        User user = userMapper.toEntity(registerDto);
        String hashedPassword = PasswordUtil.hash(registerDto.getPassword());
        user.setHashedPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User not found with id: %d", id)));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserUpdateDto updateDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User not found with id: %d", id)));

        if (!user.getUsername().equals(updateDto.getUsername().toLowerCase(Locale.ROOT).trim())) {
            boolean isExistByUsername = userRepository.existsByUsernameIgnoreCase(
                    updateDto.getUsername().toLowerCase(Locale.ROOT).trim()
            );

            if (isExistByUsername) {
                throw new AlreadyExistsException(
                        String.format("User already exists with username: %s", updateDto.getUsername())
                );
            }
        }

        userMapper.updateEntity(updateDto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User not found with id: %d", id)));
        userRepository.delete(user);
    }
}
