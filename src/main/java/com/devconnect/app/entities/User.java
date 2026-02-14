package com.devconnect.app.entities;

import com.devconnect.app.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private String username;

    @NotNull
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void preProcess() {
        if (username != null) {
            username = username.toLowerCase(Locale.ROOT).trim();
        }
        if (userRole == null) {
            userRole = UserRole.USER;
        }
    }
}
