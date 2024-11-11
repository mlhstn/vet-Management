package com.vetManagement.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "customer_id")  // Foreign key
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "animal_vaccine", // Bağlantı tablosu adı
            joinColumns = @JoinColumn(name = "animal_id"), // Hayvan ID'si
            inverseJoinColumns = @JoinColumn(name = "vaccine_id") // Aşı ID'si
    )
    private Set<Vaccine> vaccines = new HashSet<>();

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

}
