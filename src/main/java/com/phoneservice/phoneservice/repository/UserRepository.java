package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findClientByEmail(String email);
}
