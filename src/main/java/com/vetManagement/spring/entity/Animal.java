package com.vetManagement.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species;

    private String breed;

    private String gender;

    private String colour;

    private LocalDate dateOfBirth;

    @ManyToOne // Her hayvanın bir müşteriye ait olduğunu belirtir
    @JoinColumn(name = "customer_id") // Bağlantı kolonu
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "animal_vaccine", // Bağlantı tablosu adı
            joinColumns = @JoinColumn(name = "animal_id"), // Hayvan ID'si
            inverseJoinColumns = @JoinColumn(name = "vaccine_id") // Aşı ID'si
    )
    private Set<Vaccine> vaccines = new HashSet<>();

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
