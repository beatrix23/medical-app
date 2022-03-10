package org.example.utils;

public class CustomUserData {

    private Integer questionId;
    private String multipleChoiceAnswer;

    public CustomUserData(Integer questionId, String multipleChoiceAnswerId) {
        this.questionId = questionId;
        this.multipleChoiceAnswer = multipleChoiceAnswerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getMultipleChoiceAnswer() {
        return multipleChoiceAnswer;
    }

    public void setMultipleChoiceAnswerId(String multipleChoiceAnswer) {
        this.multipleChoiceAnswer = multipleChoiceAnswer;
    }

}
