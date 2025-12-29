package com.project.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.hospital_management.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Custom Queries (Examples)
    // List<Appointment> findByPatientId(Long patientId);
    // List<Appointment> findByDoctorId(Long doctorId);
}
