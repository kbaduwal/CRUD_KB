package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String studentId;
    private String department;
    private String course;
    private Integer semester;
    private LocalDate enrollmentDate;
    private LocalDate expectedGraduationDate;


}
