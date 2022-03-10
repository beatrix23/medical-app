package org.example.repository;

import org.example.domain.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Properties.DATABASE_URL;

public class AnswerRepository {

    private static final String ADD_QUERY = "INSERT INTO answer (id_question, id_appointment, free_text, multiple_choice, true_false ) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_QUERY = "SELECT * FROM answer WHERE id_appointment = ?";

    private QuestionRepository questionRepository = new QuestionRepository();

    /**
     * Adds an answer to the database according to question, appointment, and its type (free text, true/false, multiple choice)
     *
     * @param answer the answer given by the patient
     * @return the answer added
     */
    public Answer addAnswer(Answer answer) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY);
            preparedStatement.setInt(1, answer.getIdQuestion());
            preparedStatement.setInt(2, answer.getIdAppointment());
            preparedStatement.setString(3, answer.getFreeText());
            preparedStatement.setString(4, answer.getMultipleChoice());
            preparedStatement.setBoolean(5, ((answer.getTrueFalse() != null) && answer.getTrueFalse()));
            int i = preparedStatement.executeUpdate();
            connection.close();
            return answer;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * This method gets all the answers by the appointment id
     *
     * @param appointmentId the appointment id
     * @return all the answers given by the patient in order to create that appointment
     */
    public List<Answer> getAnswersByAppointment(Integer appointmentId) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setInt(1, appointmentId);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Answer> answers = new ArrayList<>();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setIdAppointment(resultSet.getInt("id_appointment"));
                answer.setIdQuestion(resultSet.getInt("id_question"));
                answer.setFreeText(resultSet.getString("free_text"));
                answer.setTrueFalse(resultSet.getBoolean("true_false"));
                answer.setMultipleChoice(resultSet.getString("multiple_choice"));
                answer.setQuestion(questionRepository.findQuestionById(answer.getIdQuestion()));
                answers.add(answer);
            }
            connection.close();
            return answers;
        } catch (SQLException sqlException) {

        }
        return new ArrayList<>();
    }
}
