package com.phoneservice.phoneservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
@Entity
@Table(name = "repairs")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneImei;
    private String problemDesc;
    private LocalDateTime startRepair;
    private LocalDateTime endRepair;
    private RepairStatus status;
    @Transient
    private Long phoneId;
    @ManyToOne
    @JoinColumn(name = "phone_id")
    private Phone phone;
//    @ManyToMany
//    private List<Part> parts;
}
