package com.devconnect.app.dtos.user;

import com.devconnect.app.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserSearchDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private List<UserRole> userRoleList;
    private LocalDateTime createdAfter;
    private LocalDateTime createdBefore;
}
