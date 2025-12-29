package com.project.hospital_management.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// JPA Relationship Notes:
// 1. The OWNING side controls & updates the foreign key column.
// 2. The INVERSE side (mappedBy) cannot update the foreign key directly.
// 3. Parent controls the lifecycle of child.
//    Example: If Patient is deleted, its Appointments should also be removed.
//    → Patient is the Parent entity in Patient ↔ Appointment relation.

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToOne
    private Doctor headDoctor;

    @ManyToMany
    // New join table will be created (not a new join column)
    // Always initialize collections to avoid NullPointerException.
    // Makes adding items safe without needing manual set operations.
    @JoinTable(name = "my_dpt_doctors", joinColumns = @JoinColumn(name = "dpt_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Doctor> doctors = new HashSet<>();
}
