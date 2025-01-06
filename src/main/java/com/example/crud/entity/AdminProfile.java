package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "admin_profiles")
public class AdminProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String employeeId;
    private String department;
    private String designation;

    @OneToMany(mappedBy = "admin")
    private List<AuditLog> auditLogs = new ArrayList<>();


}
