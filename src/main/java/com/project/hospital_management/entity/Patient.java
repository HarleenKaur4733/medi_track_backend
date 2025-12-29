package com.project.hospital_management.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.project.hospital_management.entity.type.BloodGroupType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "Patient_Data", uniqueConstraints = {
                // @UniqueConstraint(name = "unique_patient_email", columnNames = "email"),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = { "patient_name",
                                "birth_date" })
}, indexes = {
                // index se query fast to ho jati hai, but fr insertion mein time lgta hai
                @Index(name = "idx_patient_birth_date", columnList = "birth_date")
})
public class Patient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "patient_name", nullable = false, length = 40)
        private String name;

        @Column(name = "birth_date")
        private LocalDate birthDate;

        @Column(unique = true, nullable = false)
        private String email;
        private String gender;

        @CreationTimestamp
        @Column(updatable = false)
        private LocalDateTime createdAt;

        // EnumType.String to show blood group values rather than indexes
        @Enumerated(EnumType.STRING)
        private BloodGroupType bloodGroup;

        @OneToOne(cascade = {
                        CascadeType.ALL }, orphanRemoval = true)
        @JoinColumn(name = "patient_insurance_id") // owning side
        private Insurance insurance;

        @OneToMany(mappedBy = "patient", cascade = {
                        CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.EAGER) // reverse
                                                                                             // side
        @ToString.Exclude
        // return type depend on word after To, Many appointments, store as list.
        private List<Appointment> appointment;

}

// what is orphanRemoval -> if parent entity se child entity ka reference
// hata diya to child entity ko bhi delete kar dega.
// when to use orphanRemoval -> jab child entity ka existence parent entity pe
// depend
// karta ho. Jaise yaha pe agar patient entity se appointment ka reference hata
// diya to
// appointment entity ka koi matlab nahi bachta, isliye orphanRemoval = true
// kiya hai