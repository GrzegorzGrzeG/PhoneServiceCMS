package com.phoneservice.phoneservice.service;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RepairService {
    @Autowired
    private final RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public void newRepair(Repair repair) {
        repairRepository.save(repair);
    }
}
