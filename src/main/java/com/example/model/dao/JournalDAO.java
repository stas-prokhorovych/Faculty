package com.example.model.dao;

import com.example.model.dao.exception.DAOException;
import com.example.model.entity.Course;
import com.example.model.entity.Journal;

import java.util.List;

public interface JournalDAO {

    List<Journal> findJournalInfo(List<Course> finishedCourses, Integer studentId) throws DAOException;

    void endCourse(String courseId, String[] studentIds, int[] studentMarks, String[] markCode, String[] markExplanation) throws DAOException;

    void endCourse(String courseId) throws DAOException;
}
