package com.example.model.entity;

public class Student {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private boolean studentAccess;
    private int idUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public boolean isStudentAccess() {
        return studentAccess;
    }

    public void setStudentAccess(boolean studentAccess) {
        this.studentAccess = studentAccess;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
