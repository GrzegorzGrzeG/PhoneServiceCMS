package com.phoneservice.phoneservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "repairs")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startRepair;
    private LocalDateTime endRepair;
    private RepairStatus status;
    @ManyToOne
    private Phone phone;

    @ManyToMany
    private List<Part> parts;
}
