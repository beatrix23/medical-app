package org.example.utils;

import org.example.domain.Patient;
import org.example.domain.Questionnaire;
import org.example.domain.QuestionnaireCategory;

import java.time.LocalDateTime;

public class ContextHolder {

    private static ContextHolder contextHolder = new ContextHolder();
    private Patient patient;
    private QuestionnaireCategory category;
    private Questionnaire questionnaire;
    private LocalDateTime dateTime;

    private ContextHolder() {

    }

    public static ContextHolder getContext() {
        return contextHolder;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public QuestionnaireCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionnaireCategory category) {
        this.category = category;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
