package com.example.model.utils;

import com.example.model.entity.Course;
import com.example.model.entity.User;

import java.util.List;

public class CourseCatalogueInfo {
    private List<Course> courses;
    private List<User> teachers;
    private List<Integer> studentsEnrolled;

    public CourseCatalogueInfo() {
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<User> teachers) {
        this.teachers = teachers;
    }

    public List<Integer> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Integer> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }
}
