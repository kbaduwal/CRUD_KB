package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String contactEmail;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();

}
