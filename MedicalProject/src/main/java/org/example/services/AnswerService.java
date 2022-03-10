package org.example.services;

import org.example.domain.Answer;
import org.example.repository.AnswerRepository;

import java.util.List;

public class AnswerService {

    private AnswerRepository answerRepository = new AnswerRepository();

    public Answer addAnswer(Answer answer) {
        return this.answerRepository.addAnswer(answer);
    }

    public List<Answer> getAnswersByAppointmentId(Integer idAppointment) {
        return this.answerRepository.getAnswersByAppointment(idAppointment);
    }
}
