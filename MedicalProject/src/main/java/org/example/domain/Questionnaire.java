package org.example.domain;

public class Questionnaire {

    private Integer id;
    private QuestionnaireCategory category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuestionnaireCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionnaireCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", category=" + category +
                '}';
    }
}
