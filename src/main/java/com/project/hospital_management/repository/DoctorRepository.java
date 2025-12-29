package com.project.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.hospital_management.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Example custom queries:
    // Optional<Doctor> findByEmail(String email);
    // List<Doctor> findBySpecialization(String specialization);
}
