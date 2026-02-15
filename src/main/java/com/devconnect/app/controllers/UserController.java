package com.devconnect.app.controllers;

import com.devconnect.app.dtos.common.ApiResponse;
import com.devconnect.app.dtos.user.UserDto;
import com.devconnect.app.dtos.user.UserRegisterDto;
import com.devconnect.app.dtos.user.UserSearchDto;
import com.devconnect.app.dtos.user.UserUpdateDto;
import com.devconnect.app.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> register(@Valid @RequestBody UserRegisterDto registerDto) {
        UserDto userDto = userService.register(registerDto);
        return new ResponseEntity<>(
                ApiResponse.success(userDto, "User registered successfully"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getById(@PathVariable Long id) {
        UserDto userDto = userService.getById(id);
        return new ResponseEntity<>(
                ApiResponse.success(userDto, "User fetched successfully"),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAll() {
        List<UserDto> userDtoList = userService.getAll();
        return new ResponseEntity<>(
                ApiResponse.success(userDtoList, "User list fetched successfully"),
                HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserDto>>> searchAll(UserSearchDto searchDto, Sort sort) {
        List<UserDto> userDtoList = userService.search(searchDto, sort);
        return new ResponseEntity<>(
                ApiResponse.success(userDtoList, "User list fetched successfully"),
                HttpStatus.OK
        );
    }

    @GetMapping("/search/paginated")
    public ResponseEntity<ApiResponse<Page<UserDto>>> searchPaginated(
            UserSearchDto searchDto,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserDto> userDtoPage = userService.search(searchDto, pageable);
        return new ResponseEntity<>(
                ApiResponse.success(userDtoPage, "User list fetched successfully"),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDto updateDto) {
        UserDto userDto = userService.update(id, updateDto);
        return new ResponseEntity<>(
                ApiResponse.success(userDto, "User updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(
                ApiResponse.success("User deleted successfully"),
                HttpStatus.NO_CONTENT
        );
    }
}
