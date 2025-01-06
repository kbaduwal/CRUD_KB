package com.example.crud.entity;

import com.example.crud.enums.TeacherType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "teacher_profiles")
public class TeacherProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String employeeId;
    private String department;
    private String subject;
    private String designation;

    @Enumerated(EnumType.STRING)
    private TeacherType teacherType;

    private Integer maxAllowedBooks;
    private Integer extendedLoanDays;
}
