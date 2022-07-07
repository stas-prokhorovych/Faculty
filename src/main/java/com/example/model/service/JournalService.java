package com.example.model.service;

import com.example.model.entity.Course;
import com.example.model.entity.Journal;
import com.example.model.service.exception.ServiceException;

import java.util.List;

public interface JournalService {
    /**
     * End this course
     *
     * @param courseId id course to finish
     * @param studentIds student ids of this course
     * @param studentMarks marks that correspond to students ids
     * @throws ServiceException dao layer error or validation error
     */
    void endCourse(String courseId, String[] studentIds, String[] studentMarks) throws ServiceException;

    /**
     * Information about particular student
     *
     * @param finishedCourses courses that have status finished
     * @param studentId id of student
     * @return list of journal with marks
     * @throws ServiceException dao layer error or validation error
     */
    List<Journal> findJournalInfo(List<Course> finishedCourses, Integer studentId) throws ServiceException;

    /**
     *  Change course info
     *  set course status to finished
     *
     * @param courseId id of course to finish
     * @throws ServiceException dao layer error or validation error
     */
    void endCourse(String courseId) throws ServiceException;
}
