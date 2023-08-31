package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PartRepository extends JpaRepository<Part, Long> {
}
