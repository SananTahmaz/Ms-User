package com.devconnect.app.repositories;

import com.devconnect.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsernameIgnoreCase(String username);
}
