package com.project.hospital_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospital_management.entity.Patient;
import com.project.hospital_management.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Transactional
    // @Transactional means: One database session + one persistence context
    // Shared for the whole method

    // What happens WITHOUT @Transactional
    // Each repository call: Opens a new session. Has a new persistence context
    // Loads a new Patient object
    public Patient getPatientById(Long id) {
        Patient p1 = patientRepository.findById(id).orElseThrow();
        Patient p2 = patientRepository.findById(id).orElseThrow();
        System.out.println(p1 == p2);

        // without even calling save method, since its in
        // persistent state, db will be updated
        p1.setName("Developer");
        return p1;

    }

}
