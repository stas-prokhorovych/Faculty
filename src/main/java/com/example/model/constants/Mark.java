package com.example.model.constants;

/**
 * Enum for marks
 * used Bologna Process marks system
 */
public enum Mark {
    A("A", "Excellent"),
    B("B", "Very good"),
    C("C", "Good"),
    D("D", "Satisfactory"),
    E("E", "Sufficient"),
    FX("FX", "Fail"),
    F("F", "Fail");

    private final String code;
    private final String explanation;

    Mark(String code, String explanation) {
        this.code = code;
        this.explanation = explanation;
    }

    public String getCode() {
        return code;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public String toString() {
        return code;
    }
}
