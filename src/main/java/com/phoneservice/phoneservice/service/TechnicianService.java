package com.phoneservice.phoneservice.service;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.repository.RepairRepository;
import com.phoneservice.phoneservice.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {
    @Autowired
    private final TechnicianRepository technicianRepository;

    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;

    }


}
