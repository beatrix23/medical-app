package org.example.domain;

import java.time.LocalDateTime;

public class Appointment {

    private Integer id;
    private Integer patientId;
    private LocalDateTime dateTime;
    private QuestionnaireCategory category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public QuestionnaireCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionnaireCategory category) {
        this.category = category;
    }
}
