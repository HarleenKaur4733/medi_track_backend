package com.project.hospital_management;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospital_management.entity.Appointment;
import com.project.hospital_management.entity.Insurance;
import com.project.hospital_management.entity.Patient;
import com.project.hospital_management.service.AppointmentService;
import com.project.hospital_management.service.InsuranceService;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance() {
        Insurance insurance = new Insurance();
        insurance.setPolicyNumber("HDFC_123");
        insurance.setProvider("HDFC");
        insurance.setValidUntil(LocalDate.of(2027, 12, 12));

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);
    }

    @Test
    public void testCreateAppointment() {
        // Test logic for creating an appointment can be added here
        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(LocalDateTime.of(2025, 12, 29, 6, 30));
        appointment.setReason("Regular Checkup");
        Appointment savedAppointment = appointmentService.createNewAppointment(appointment, 2L, 1L);
        System.out.println(savedAppointment);

        // Test logic for reassigning an appointment can be added here
        Appointment reassignedAppointment = appointmentService
                .reassignAppointmentToAnotherDoctor(savedAppointment.getId(), 3L);
        System.out.println(reassignedAppointment);
    }

    @Test
    public void testDissociateInsurance() {
        Patient patient = insuranceService.dissociatePatientFromInsurance(1L);
        System.out.println(patient);
    }

    // @Test
    // public void testReassignAppointment() {
    // // Test logic for reassigning an appointment can be added here
    // Appointment reassignedAppointment =
    // appointmentService.reassignAppointmentToAnotherDoctor(1L, 3L);
    // System.out.println(reassignedAppointment);
    // }
}
