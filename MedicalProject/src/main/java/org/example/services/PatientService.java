package org.example.services;

import org.example.domain.Patient;
import org.example.repository.PatientRepository;

public class PatientService {

    private PatientRepository patientRepository = new PatientRepository();


    public Patient addPatient(Patient patient) {
        return patientRepository.addPatient(patient);
    }

    public Patient findPatient(String email) {
        return patientRepository.getPatientByEmail(email);
    }

    public Patient updatePatient(Patient patient) {
        return this.patientRepository.updatePatientInfo(patient);
    }

    public boolean logIn(String email, String password) {
        Patient patient = findPatient(email);
        if (patient != null) {
            return patient.getPassword().equals(password);
        }
        return false;
    }

    public void deletePatient(String email) {
        this.patientRepository.deletePatient(email);
    }
}
