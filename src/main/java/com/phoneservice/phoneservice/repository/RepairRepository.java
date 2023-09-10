package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.Repair;
import com.phoneservice.phoneservice.entity.RepairStatus;
import com.phoneservice.phoneservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    Repair findRepairById(Long id);

    @Query("UPDATE Repair r SET r.status =?1")
    void updateRepair(RepairStatus repairStatus);

    @Query(value = "SELECT * FROM repairs WHERE status LIKE 1", nativeQuery = true)
    List<Repair> findRepairWhereStatusIs2();

    @Query(value = "SELECT * FROM repairs WHERE status LIKE 0", nativeQuery = true)
    List<Repair> findActiveRepairs();

    @Query("SELECT r FROM Repair r WHERE r.user = :email")
    List<Repair> findRepairByUser(@Param(value = "email")String email);

    @Query("SELECT r FROM Repair r WHERE r.user = :user")
    List<Repair> findRepairByUserObj(@Param(value = "user") User user);





}
