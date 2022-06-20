package com.example.model.service.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.Course;
import com.example.model.exception.CourseServiceException;
import com.example.model.service.CourseService;
import com.example.model.utils.pagination.CourseCatalogueInfo;

import java.util.List;

public class MySqlCourseService implements CourseService {
    private static CourseDAO courseDAO;
    private static DaoFactory daoFactory;
    private static MySqlCourseService instance;

    private MySqlCourseService() {
        try {
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            courseDAO = daoFactory.getCourseDAO();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static CourseService getInstance() {
        if (instance == null) {
            instance = new MySqlCourseService();
        }
        return instance;
    }

    @Override
    public void deleteCourse(int id) {
        courseDAO.deleteCourse(id);
    }

    @Override
    public void addCourse(Course course) throws CourseServiceException {
        Course courseWithThisName = courseDAO.findCourseByName(course.getName());
        if (courseWithThisName != null) {
            throw new CourseServiceException("course with such name already exist");
        }
        if (course.getStartDate().isAfter(course.getEndDate())) {
            throw new CourseServiceException("start date must be before end date");
        }

        System.out.println("here");
        courseDAO.addCourse(course);
    }

    @Override
    public List<String> findThemes() {
        return courseDAO.findThemes();
    }

    @Override
    public int findNumberOfRecords(String theme, Integer teacher) {
        if (theme != null) {
            return courseDAO.findNumberOfRecordsByTheme(theme);
        } else if (teacher != null) {
            return courseDAO.findNumberOfRecordsByTeacher(teacher);
        }
        return courseDAO.findNumberOfRecords();
    }

    @Override
    public void updateCourse(String name, int id) {
        courseDAO.updateCourse(name, id);
    }

    @Override
    public List<Course> getNoTeacherCourses() {
        return courseDAO.getNoTeacherCourses();
    }


    @Override
    public List<Course> findAllFinishedCoursesByTeacherId(int teacherId) {
        return courseDAO.findAllFinishedCoursesByTeacherId(teacherId);
    }

    @Override
    public List<Course> findRegisteredCoursesByStudentId(Integer studentId) {
        return courseDAO.findRegisteredCoursesByStudentId(studentId);
    }

    @Override
    public List<Course> findInProgressCoursesByStudentId(Integer studentId) {
        return courseDAO.findInProgressCoursesByStudentId(studentId);
    }

    @Override
    public List<Course> findAllInProgressCoursesByTeacherId(Integer teacherId) {
        return courseDAO.findAllInProgressCoursesByTeacherId(teacherId);
    }

    @Override
    public List<Course> findFinishedCoursesByStudentId(Integer studentId) {
        return courseDAO.findFinishedCoursesByStudentId(studentId);
    }

    @Override
    public Course findCourseById(String id) {
        return courseDAO.findCourseById(id);
    }

    @Override
    public CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String type, String theme, Integer teacherId, String sort, String order) {
        return courseDAO.findCoursesByPage(offset, recordsPerPage, type, theme, teacherId, sort, order);
    }


}
