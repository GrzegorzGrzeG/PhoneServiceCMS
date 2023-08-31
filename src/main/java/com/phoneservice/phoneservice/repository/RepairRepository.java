package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.RepairStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    Repair findRepairById(Long id);

    @Query("UPDATE Repair r SET r.status =?1")
    void updateRepair(RepairStatus repairStatus);

}
