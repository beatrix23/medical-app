package org.example.repository;

import org.example.domain.Patient;

import java.sql.*;

import static org.example.utils.Properties.DATABASE_URL;

public class PatientRepository {
    private static final String FIND_QUERY = "SELECT * FROM patient WHERE email = ?";
    private static final String ADD_QUERY = "INSERT INTO patient (email, password) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE patient SET name = ?, age = ?, telephone = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM patient WHERE email = ?";

    /**
     * This method is searching in the database a patient by its email
     *
     * @param email the patient's email
     * @return the patient with all his attributes (found in the database)
     */
    public Patient getPatientByEmail(String email) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setString(1, email);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Patient patient = new Patient();
            patient.setId(resultSet.getInt("id"));
            patient.setAge(resultSet.getInt("age"));
            patient.setName(resultSet.getString("name"));
            patient.setTelephone(resultSet.getString("telephone"));
            patient.setEmail(resultSet.getString("email"));
            patient.setPassword(resultSet.getString("password"));
            connection.close();
            return patient;
        } catch (SQLException sqlException) {

        }
        return null;
    }

    /**
     * Adds a patient in the database (only the email and password attributes given in the log in/sign up field)
     *
     * @param patient the patient to add
     * @return the added patient
     */
    public Patient addPatient(Patient patient) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY);
            preparedStatement.setString(1, patient.getEmail());
            preparedStatement.setString(2, patient.getPassword());
            int i = preparedStatement.executeUpdate();
            Patient addedPatient = getPatientByEmail(patient.getEmail());
            connection.close();
            return addedPatient;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * Updates a patient attributes
     *
     * @param patient the patient to update (it is searched by email)
     * @return the updated patient
     */
    public Patient updatePatientInfo(Patient patient) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setString(3, patient.getTelephone());
            preparedStatement.setInt(4, patient.getId());
            int i = preparedStatement.executeUpdate();
            Patient updatedPatient = getPatientByEmail(patient.getEmail());
            connection.close();
            return updatedPatient;
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return null;
    }

    /**
     * Deletes a patient and it is used only for tests
     *
     * @param email the patient email
     */
    public void deletePatient(String email) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
