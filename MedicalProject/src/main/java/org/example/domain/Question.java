package org.example.domain;

import java.util.List;

public class Question {

    private Integer id;
    private Integer questionnaireId;
    private String questionText;
    private AnswerType answerType;
    private List<MultipleChoiceAnswerPosibilities> multipleChoiceAnswerPosibilities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }


    public void setQuestionnaireId(Integer questionId) {
        this.questionnaireId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public List<MultipleChoiceAnswerPosibilities> getMultipleChoiceAnswerPosibilities() {
        return multipleChoiceAnswerPosibilities;
    }

    public void setMultipleChoiceAnswerPosibilities(List<MultipleChoiceAnswerPosibilities> multipleChoiceAnswerPosibilities) {
        this.multipleChoiceAnswerPosibilities = multipleChoiceAnswerPosibilities;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionnaireId=" + questionnaireId +
                ", questionText='" + questionText + '\'' +
                ", answerType=" + answerType +
                ", multipleChoiceAnswerPosibilities=" + multipleChoiceAnswerPosibilities +
                '}';
    }
}
