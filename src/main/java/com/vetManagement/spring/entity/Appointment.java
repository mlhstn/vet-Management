package com.vetManagement.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate appointmentDate;

    @ManyToOne
    @JoinColumn(name = "animal_id") // Bağlantı kolonu
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "doctor_id") // Bağlantı kolonu
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Bağlantı kolonu
    private Customer customer;
}
