package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findClientByEmail(String email);
    @Query("SELECT u from User u WHERE u.id = :id")
    User findClientById(@Param("id") Long id);

}
