package com.example.model.dao;

import java.sql.SQLException;

public interface JournalDAO {
    void endCourse(String courseId, String[] studentIds, String[] studentMarks);
}
