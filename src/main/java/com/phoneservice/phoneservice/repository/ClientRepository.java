package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
