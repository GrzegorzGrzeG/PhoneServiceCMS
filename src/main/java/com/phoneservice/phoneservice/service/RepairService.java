package com.phoneservice.phoneservice.service;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.RepairStatus;
import com.phoneservice.phoneservice.entity.User;
import com.phoneservice.phoneservice.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        repair.setStatus(RepairStatus.IN_REPAIR);
        repairRepository.save(repair);
    }

    public void updateRepair(Repair repair) {
        repairRepository.save(repair);
    }

    public Repair repairDetails(Long id) {
        return repairRepository.findRepairById(id);
    }

    public List<Repair> getAll() {
        return repairRepository.findActiveRepairs();
    }

    public List<Repair> finished() {
        return repairRepository.findRepairWhereStatusIs2();
    }

    public Repair getById(Long id) {
        return repairRepository.findRepairById(id);
    }

    public List<Repair> getByUser(String email) {
        return repairRepository.findRepairByUser(email);
    }

    public List<Repair> getByUserObj(User user) {
        return repairRepository.findRepairByUserObj(user);
    }


}
