package com.project.hospital_management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// How to decide which is owning side and which is reverse side?
// Patient can be there without appointment, but there is no appointment 
// without patient -> Appointment needs patient -> Appointment is owning side. 
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    // read it like this -> Many {classname} Appointments have one patient
    @ManyToOne
    // appointment without patient does not make any sense
    // so when u make appointment, patient must be there
    @JoinColumn(nullable = false)
    private Patient patient;
    // return type depend on word after To -> ManyToOne -> One patient

    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;
}

// cascading ka khyaal rkhna, agr appointment delete kiya to patient
// delete na ho jaye, isliye cascade nahi lagaya....

// but hn, agr patient delete kiya to appointment bhi delete ho jaye
// isliye Patient entity mein cascade lagaaya hoga.
