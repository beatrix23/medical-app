package org.example.repository;

import org.example.domain.AnswerType;
import org.example.domain.Question;
import org.example.domain.Questionnaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Properties.DATABASE_URL;

public class QuestionRepository {

    private static final String FIND_QUERY = "SELECT * FROM question WHERE questionaire_id = ?";
    private static final String FIND_ONE_QUERY = "SELECT * FROM question WHERE id = ?";
    private MultipleChoiceAnswerPosibilitiesRepository multipleChoiceAnswerPosibilitiesRepository = new MultipleChoiceAnswerPosibilitiesRepository();

    /**
     * Gets the list of questions for the selected questionnaire category
     *
     * @param questionnaire the questionnaire selected by patient (by its category)
     * @return the list of questions for that questionnaire
     */
    public List<Question> getQuestionsByQuestionnaire(Questionnaire questionnaire) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setInt(1, questionnaire.getId());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestionnaireId(resultSet.getInt("questionaire_id"));
                question.setQuestionText(resultSet.getString("text"));
                question.setAnswerType(AnswerType.valueOf(resultSet.getString("answer_type")));
                if (question.getAnswerType() == AnswerType.MULTIPLE_CHOICE) {
                    question.setMultipleChoiceAnswerPosibilities(multipleChoiceAnswerPosibilitiesRepository.getMultipleChoiceAnswerPossibilitiesByQuestionId(question));
                } else {
                    question.setMultipleChoiceAnswerPosibilities(new ArrayList<>());
                }
                questions.add(question);
            }
            connection.close();
            return questions;
        } catch (SQLException sqlException) {

        }
        return new ArrayList<>();
    }

    /**
     * Gets the question by its id
     *
     * @param id the id of the question
     * @return the question which matches the id
     */
    public Question findQuestionById(Integer id) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Question question = new Question();
            question.setQuestionText(resultSet.getString("text"));
            question.setQuestionnaireId(resultSet.getInt("questionaire_id"));
            question.setId(resultSet.getInt("id"));
            question.setAnswerType(AnswerType.valueOf(resultSet.getString("answer_type")));
            if (question.getAnswerType() == AnswerType.MULTIPLE_CHOICE) {
                question.setMultipleChoiceAnswerPosibilities(multipleChoiceAnswerPosibilitiesRepository.getMultipleChoiceAnswerPossibilitiesByQuestionId(question));
            } else {
                question.setMultipleChoiceAnswerPosibilities(new ArrayList<>());
            }
            connection.close();
            return question;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
