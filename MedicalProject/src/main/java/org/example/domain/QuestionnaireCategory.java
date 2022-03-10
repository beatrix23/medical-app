package org.example.domain;

public enum QuestionnaireCategory {
    OPHTHALMOLOGY("Oftalmologie"),
    CARDIOLOGY("Cardiologie"),
    GASTRO("Gastro-Enterologie");

    private String name;

    QuestionnaireCategory(String name) {
        this.name = name;
    }

    public static QuestionnaireCategory getFromName(String name) {
        for (QuestionnaireCategory q : QuestionnaireCategory.values()) {
            if (q.getName().equals(name)) {
                return q;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
