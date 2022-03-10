package org.example.domain;

public class MultipleChoiceAnswerPosibilities {

    private Integer id;
    private Integer questionId;
    private String possibleAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getPossibleAnswer() {
        return possibleAnswer;
    }

    public void setPossibleAnswer(String possibleAnswer) {
        this.possibleAnswer = possibleAnswer;
    }

    @Override
    public String toString() {
        return "MultipleChoiceAnswerPosibilities{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", possibleAnswer='" + possibleAnswer + '\'' +
                '}';
    }
}
