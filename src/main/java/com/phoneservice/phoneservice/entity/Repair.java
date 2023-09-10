package com.phoneservice.phoneservice.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import java.time.LocalDate;
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
    private LocalDate dateOfRegistration;
    private LocalDateTime endRepair;
    private RepairStatus status;
    private Double sum;
    @Transient
    private Long phoneId;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @ManyToMany
    private List<Part> parts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void register() {
        dateOfRegistration = LocalDate.now();
    }
}
