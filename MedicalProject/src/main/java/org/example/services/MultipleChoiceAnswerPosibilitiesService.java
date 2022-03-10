package org.example.services;

import org.example.domain.MultipleChoiceAnswerPosibilities;
import org.example.domain.Question;
import org.example.repository.MultipleChoiceAnswerPosibilitiesRepository;

import java.util.List;

public class MultipleChoiceAnswerPosibilitiesService {

    private MultipleChoiceAnswerPosibilitiesRepository multipleChoiceRepository = new MultipleChoiceAnswerPosibilitiesRepository();

    public List<MultipleChoiceAnswerPosibilities> getMultipleChoicePossibilitiesByQuestionId(Question question) {
        return this.multipleChoiceRepository.getMultipleChoiceAnswerPossibilitiesByQuestionId(question);
    }
}
