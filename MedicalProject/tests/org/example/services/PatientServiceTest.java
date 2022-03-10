package org.example.services;

import org.example.domain.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientServiceTest {

    @Test
    public void addPatientSuccessfully() {
        PatientService patientService = new PatientService();
        Patient patient = new Patient();
        String patientEmail = "testing";
        patient.setEmail(patientEmail);
        patient.setPassword("testPass");
        patientService.addPatient(patient);

        Assertions.assertEquals(patientEmail, patientService.findPatient(patientEmail).getEmail());
        patientService.deletePatient(patientEmail);
    }

    @org.junit.jupiter.api.Test
    public void updatePatientSuccessfully() {
        PatientService patientService = new PatientService();
        Patient patient = new Patient();
        String email = "testUpdate2";
        patient.setEmail(email);
        patient.setPassword("testUpdate");
        patient = patientService.addPatient(patient);
        String name = "testName";
        patient.setName(name);
        patient.setTelephone("000");
        patient.setAge(100);
        patientService.updatePatient(patient);


        Assertions.assertEquals(name, patientService.findPatient(email).getName());
        patientService.deletePatient(email);
    }

    @Test
    public void logInTest() {
        PatientService patientService = new PatientService();
        Patient patient = new Patient();
        String email = "testUpdate2";
        patient.setEmail(email);
        patient.setPassword("testUpdate");
        patient = patientService.addPatient(patient);

        Assertions.assertTrue(patientService.logIn(patient.getEmail(), patient.getPassword()));
        Assertions.assertFalse(patientService.logIn(patient.getEmail(), patient.getPassword() + "pass"));
        patientService.deletePatient(email);
    }
}
