package org.example.services;

import org.example.domain.Question;
import org.example.domain.Questionnaire;
import org.example.repository.QuestionRepository;

import java.util.List;

public class QuestionService {

    private QuestionRepository questionRepository = new QuestionRepository();

    public List<Question> getQuestionsByQuestionnaire(Questionnaire questionnaire) {
        return this.questionRepository.getQuestionsByQuestionnaire(questionnaire);
    }

    public Question findQuestionById(Integer id) {
        return this.questionRepository.findQuestionById(id);
    }
}
