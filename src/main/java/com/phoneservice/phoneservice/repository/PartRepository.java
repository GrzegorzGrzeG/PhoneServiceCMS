package com.phoneservice.phoneservice.repository;

import com.phoneservice.phoneservice.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PartRepository extends JpaRepository<Part, Long> {

    Part findPartById(Long id);
    @Modifying
    @Query("UPDATE Part p SET p.quantity = :quantity, p.price = :price WHERE p.id = :id")
    void updateById(@Param(value = "id")Long id, @Param(value = "quantity")Integer quantity, @Param(value = "price") Double price);

}
