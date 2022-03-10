package org.example.services;

import org.example.domain.Questionnaire;
import org.example.domain.QuestionnaireCategory;
import org.example.repository.QuestionnaireRepository;

public class QuestionnaireService {

    private QuestionnaireRepository questionnaireRepository = new QuestionnaireRepository();

    public Questionnaire getQuestionnaireByCategory(QuestionnaireCategory questionnaireCategory) {
        return this.questionnaireRepository.getQuestionnaireByCategory(questionnaireCategory);
    }
}
