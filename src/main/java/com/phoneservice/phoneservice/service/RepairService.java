package com.phoneservice.phoneservice.service;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.RepairStatus;
import com.phoneservice.phoneservice.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    @Autowired
    private final RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public void newRepair(Repair repair) {
        repair.setStatus(RepairStatus.REGISTERED);
        repairRepository.save(repair);
    }

    public Repair repairDetails(Long id) {
         return repairRepository.findRepairById(id);
    }

    public List<Repair> getAll() {
         return repairRepository.findAll();
    }

    public void updateStatus(RepairStatus repairStatus) {
        repairRepository.updateRepair(repairStatus);
    }

//    public void update(Repair repair) {
//        repairRepository.updateByStatus(repair);
//    }
}
