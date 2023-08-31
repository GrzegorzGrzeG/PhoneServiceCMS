package com.phoneservice.phoneservice.service;

import com.phoneservice.phoneservice.entity.Phone;
import com.phoneservice.phoneservice.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    @Autowired
    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void addNewPhone(Phone phone) {
        phoneRepository.save(phone);
    }

    public List<Phone> getAll() {
         return phoneRepository.findAll();
    }

    public Phone getPhoneById(Long id) {
        return phoneRepository.findPhoneById(id);
    }
}
