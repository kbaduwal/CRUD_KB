package com.example.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admins {

    private Long id;
    private String name;


}
