package com.example.model.dao;

import com.example.model.dao.exception.DAOException;
import com.example.model.entity.Course;
import com.example.model.entity.Journal;

import java.util.List;

public interface JournalDAO {

    /**
     * Information about particular student
     *
     * @param finishedCourses courses that have status finished
     * @param studentId id of student
     * @return list of journal with marks
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<Journal> findJournalInfo(List<Course> finishedCourses, Integer studentId) throws DAOException;

    /**
     * End this course
     *
     * @param courseId id course to finish
     * @param studentIds student ids of this course
     * @param studentMarks marks that correspond to students ids
     * @param markCode marks code that correspond to student ids
     * @param markExplanation marks explanation that correspond to student ids
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void endCourse(String courseId, String[] studentIds, int[] studentMarks, String[] markCode, String[] markExplanation) throws DAOException;

    /**
     *  Change course info
     *  set course status to finished
     *
     * @param courseId id of course to finish
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void endCourse(String courseId) throws DAOException;
}
