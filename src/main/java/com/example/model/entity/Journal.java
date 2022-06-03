package com.example.model.entity;

public class Journal {
    private int id;
    private int idStudentCourse;
    private int mark;

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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
