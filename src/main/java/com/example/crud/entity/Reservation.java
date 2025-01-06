package com.example.crud.entity;

import com.example.crud.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private User user;

    private LocalDate reservationDate;
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
