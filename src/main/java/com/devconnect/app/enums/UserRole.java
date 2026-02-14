package com.devconnect.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private final String label;

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static UserRole fromValue(String value) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(value) || userRole.label.equalsIgnoreCase(value)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid user role: %s", value));
    }
}
