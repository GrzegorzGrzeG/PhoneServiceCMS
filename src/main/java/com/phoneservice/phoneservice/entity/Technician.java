package com.phoneservice.phoneservice.entity;

import jakarta.persistence.*;

import java.util.List;

//@Entity
//@Table(name = "technicians")
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;

//    @OneToMany
//    private List<Repair> repairs;


}
