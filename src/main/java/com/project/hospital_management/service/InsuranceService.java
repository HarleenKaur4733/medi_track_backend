package com.project.hospital_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hospital_management.entity.Insurance;
import com.project.hospital_management.entity.Patient;
import com.project.hospital_management.repository.InsuranceRepository;
import com.project.hospital_management.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("No patient found with this id"));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return patient;
    }

    // understand concept of orphanRemoval
    @Transactional
    public Patient dissociatePatientFromInsurance(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("No patient found with this id"));
        // patient ne bola ki mai ab insurance nhi smbalta..toh jpa usko db se bhi
        // delete krdega
        patient.setInsurance(null);
        return patient;

    }
}
