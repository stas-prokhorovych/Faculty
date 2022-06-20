package com.example.model.dao;

public interface JournalDAO {
    void endCourse(String courseId, String[] studentIds, String[] studentMarks);
}
