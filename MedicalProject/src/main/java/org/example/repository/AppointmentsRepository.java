package org.example.repository;

import org.example.domain.Appointment;
import org.example.domain.QuestionnaireCategory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Properties.DATABASE_URL;

public class AppointmentsRepository {

    private static final String FIND_QUERY = "SELECT * FROM appointment WHERE patient_id = ?";
    private static final String ADD_QUERY = "INSERT INTO appointment (patient_id, date_time, category ) VALUES (?, ?, ?)";

    /**
     * This method gets all the appointments of one patient
     *
     * @param id the patient's id
     * @return all the appointments created by that patient
     */
    public List<Appointment> getAppointmentsByPatientId(Integer id) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setInt(1, id);
            List<Appointment> appointments = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("id"));
                appointment.setPatientId(resultSet.getInt("patient_id"));
                appointment.setCategory(QuestionnaireCategory.valueOf(resultSet.getString("category")));
                appointment.setDateTime(LocalDateTime.parse(resultSet.getString("date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                appointments.add(appointment);
            }
            connection.close();
            return appointments;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * Adds an appointment to the database
     *
     * @param appointment the appointment object (patient id, category-eyes, gastro, cardio- and its date)
     * @return the appointment added
     */
    public Appointment addAppointment(Appointment appointment) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, appointment.getPatientId());
            preparedStatement.setString(2, appointment.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            preparedStatement.setString(3, appointment.getCategory().name());
            int i = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            appointment.setId(resultSet.getInt(1));
            connection.close();
            return appointment;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
