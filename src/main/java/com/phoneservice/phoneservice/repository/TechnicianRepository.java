package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
}