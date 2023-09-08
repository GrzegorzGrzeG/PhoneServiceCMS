package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Phone findPhoneById(Long id);
    Phone findPhoneByModel(String model);
}
