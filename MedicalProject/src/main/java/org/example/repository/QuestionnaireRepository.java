package org.example.repository;

import org.example.domain.Questionnaire;
import org.example.domain.QuestionnaireCategory;

import java.sql.*;

import static org.example.utils.Properties.DATABASE_URL;


public class QuestionnaireRepository {

    private static final String FIND_QUERY = "SELECT * FROM questionaire WHERE category = ?";

    /**
     * Gets a questionnaire by its category (eyes, cardio, gastro)
     *
     * @param category the enum (eyes, cardio, gastro)
     * @return the questionnaire which category matches the param
     */
    public Questionnaire getQuestionnaireByCategory(QuestionnaireCategory category) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setString(1, category.name());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setId(resultSet.getInt("id"));
            questionnaire.setCategory(QuestionnaireCategory.valueOf(resultSet.getString("category")));
            connection.close();
            return questionnaire;
        } catch (SQLException sqlException) {

        }
        return null;
    }
}
