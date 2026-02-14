package com.devconnect.app.dtos.user;

import com.devconnect.app.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterDto {
    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    private String middleName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private UserRole userRole;
}
