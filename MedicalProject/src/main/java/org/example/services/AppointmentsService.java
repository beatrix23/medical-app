package org.example.services;

import org.example.domain.Appointment;
import org.example.repository.AppointmentsRepository;

import java.util.List;

public class AppointmentsService {

    private AppointmentsRepository appointmentsRepository = new AppointmentsRepository();

    public Appointment addAppointment(Appointment appointment) {
        return this.appointmentsRepository.addAppointment(appointment);
    }

    public List<Appointment> getAppointmentsForPatient(Integer id) {
        return this.appointmentsRepository.getAppointmentsByPatientId(id);
    }
}
