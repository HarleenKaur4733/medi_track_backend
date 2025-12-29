package com.project.hospital_management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hospital_management.entity.Appointment;
import com.project.hospital_management.repository.AppointmentRepository;
import com.project.hospital_management.repository.DoctorRepository;
import com.project.hospital_management.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId) {
        // Implementation for creating a new appointment
        appointment.setPatient(patientRepository.findById(patientId).orElse(null));
        appointment.setDoctor(doctorRepository.findById(doctorId).orElse(null));
        return appointmentRepository.save(appointment);
    }

    // Dirty Checking = JPA automatically updates changed managed entities
    @Transactional
    public Appointment reassignAppointmentToAnotherDoctor(Long appointmentId, Long newDoctorId) {
        // Implementation for reassigning an appointment to another doctor
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        // This will automatically call update since its dirty
        appointment.setDoctor(doctorRepository.findById(newDoctorId).orElse(null));
        return appointment;

    }
}

// If entity comes from DB → managed → no save() needed after modifications.
// If entity is new object → transient → must call save() to insert.
