package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;
    private String address;
    private LocalDate createdAt;
    private LocalDate lastLogin;
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private TeacherProfile teacherProfile;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private AdminProfile adminProfile;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private LibraryCard libraryCard;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans = new ArrayList<>();

}
