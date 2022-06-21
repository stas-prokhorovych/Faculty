package com.example.model.entity;

public class Journal {
    private int id;
    private int idStudentCourse;
    private int markPoints;
    private String markCode;
    private String markExplanation;

    public Journal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStudentCourse() {
        return idStudentCourse;
    }

    public void setIdStudentCourse(int idStudentCourse) {
        this.idStudentCourse = idStudentCourse;
    }

    public int getMarkPoints() {
        return markPoints;
    }

    public void setMarkPoints(int markPoints) {
        this.markPoints = markPoints;
    }

    public String getMarkCode() {
        return markCode;
    }

    public void setMarkCode(String markCode) {
        this.markCode = markCode;
    }

    public String getMarkExplanation() {
        return markExplanation;
    }

    public void setMarkExplanation(String markExplanation) {
        this.markExplanation = markExplanation;
    }
}
