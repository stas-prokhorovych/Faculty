package com.example.model.service;

import com.example.model.entity.Course;
import com.example.model.entity.Journal;
import com.example.model.service.exception.ServiceException;

import java.util.List;

public interface JournalService {
    void endCourse(String courseId, String[] studentIds, String[] studentMarks) throws ServiceException;

    List<Journal> findJournalInfo(List<Course> finishedCourses, Integer studentId) throws ServiceException;

    void endCourse(String courseId) throws ServiceException;
}
