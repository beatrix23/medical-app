package org.example.repository;

import org.example.domain.MultipleChoiceAnswerPosibilities;
import org.example.domain.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Properties.DATABASE_URL;

public class MultipleChoiceAnswerPosibilitiesRepository {

    private static final String FIND_QUERY = "SELECT * FROM multiple_choice_answer_posibilities WHERE question_id = ?";

    /**
     * this method gets the multiple choice answer possibilities to a question that is multiple choice type
     *
     * @param question the question that is multiple choice and needs answer possibilities/variants
     * @return returns all the answer possibilities to that question
     */
    public List<MultipleChoiceAnswerPosibilities> getMultipleChoiceAnswerPossibilitiesByQuestionId(Question question) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setInt(1, question.getId());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet resultSet = preparedStatement.executeQuery();
            List<MultipleChoiceAnswerPosibilities> multipleChoices = new ArrayList<>();
            while (resultSet.next()) {
                MultipleChoiceAnswerPosibilities multipleChoice = new MultipleChoiceAnswerPosibilities();
                multipleChoice.setId(resultSet.getInt("id"));
                multipleChoice.setQuestionId(resultSet.getInt("question_id"));
                multipleChoice.setPossibleAnswer(resultSet.getString("possible_answer"));
                multipleChoices.add(multipleChoice);
            }
            connection.close();
            return multipleChoices;
        } catch (SQLException sqlException) {

        }
        return null;
    }
}
