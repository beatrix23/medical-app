package org.example.domain;

public class Answer {

    private Integer id;
    private Integer idQuestion;
    private Integer idAppointment;
    private String freeText;
    private String multipleChoice;
    private Boolean trueFalse;
    private Question question;

    public Answer() {

    }

    public Answer(Integer idQuestion, String freeText, String multipleChoice, Boolean trueFalse) {
        this.idQuestion = idQuestion;
        this.freeText = freeText;
        this.multipleChoice = multipleChoice;
        this.trueFalse = trueFalse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Integer getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public String getMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(String multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    public Boolean getTrueFalse() {
        return trueFalse;
    }

    public void setTrueFalse(Boolean trueFalse) {
        this.trueFalse = trueFalse;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", idQuestion=" + idQuestion +
                ", idAppointment=" + idAppointment +
                ", freeText='" + freeText + '\'' +
                ", multipleChoice='" + multipleChoice + '\'' +
                ", trueFalse=" + trueFalse +
                '}';
    }
}
