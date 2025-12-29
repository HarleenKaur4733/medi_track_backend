package com.project.hospital_management;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospital_management.dto.BloodGroupTypeResponseEntity;
import com.project.hospital_management.entity.Patient;
import com.project.hospital_management.entity.type.BloodGroupType;
import com.project.hospital_management.repository.PatientRepository;
import com.project.hospital_management.service.PatientService;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository() {
        // N+1 query problem solution
        // List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientList = patientRepository.findAllPatientsWithAppointments();
        System.out.println("patientList" + patientList);
    }

    @Test
    public void testPatientService() {
        // Patient P1 = patientService.getPatientById(1L);
        // System.out.println(P1);

        // List<Patient> patientList = patientRepository.findByNameOrEmail("Rohit
        // Verma", "ananya.gupta@gmail.com");
        // List<Patient> patientList =
        // patientRepository.findByBloodGroup(BloodGroupType.AB_POSITIVE);
        // List<Patient> patientList =
        // patientRepository.findByBornAfterDate(LocalDate.of(1988, 1, 1));
        // List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();

        // for (Object[] objects : bloodGroupList) {
        // System.out.println(objects[0] + " " + objects[1]);
        // }

        // List<Patient> patientList = patientRepository.findAllPatients();
        // for (Patient patient : patientList) {
        // System.out.println(patient.toString());
        // }

        // int affectedRows = patientRepository.updateNameWithId("Anaya Gupta", 1L);
        // System.out.println(affectedRows);

        List<BloodGroupTypeResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
        for (BloodGroupTypeResponseEntity responseDto : bloodGroupList) {
            System.out.println(responseDto.getBloodGroup() + "||" + responseDto.getCount());
        }

    }

}
