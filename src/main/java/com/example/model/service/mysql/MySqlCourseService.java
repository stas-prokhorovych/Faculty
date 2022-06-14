package com.example.model.service.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.Course;
import com.example.model.service.CourseService;
import com.example.model.utils.Utils;

import java.util.List;

public class MySqlCourseService implements CourseService {
    private static CourseDAO courseDAO;
    private static DaoFactory daoFactory;
    private static MySqlCourseService instance;

    private MySqlCourseService() {
        try{
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            courseDAO = daoFactory.getCourseDAO();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static CourseService getInstance() {
        if(instance == null) {
            instance = new MySqlCourseService();
        }
        return instance;
    }


    @Override
    public void deleteCourse(int id) {
        courseDAO.deleteCourse(id);
    }

    @Override
    public void addCourse(Course course) {
        courseDAO.addCourse(course);
    }

    @Override
    public List<String> findThemes() {
        return courseDAO.findThemes();
    }

    @Override
    public List<Course> findAllCoursesByPage(int offset, int noOfRecords, String theme, Integer teacher, String sort, String order) {
        List<Course> courses;
        if(theme != null) {
            courses = courseDAO.findAllCoursesByThemeByPage(theme, offset, noOfRecords);
        } else if(teacher != null) {
            courses = courseDAO.findAllCoursesByTeacherByPage(teacher, offset, noOfRecords);
        } else {
            courses = courseDAO.findAllCoursesByPage(offset, noOfRecords);
        }

        if(sort != null && order != null) {
            courses = Utils.sortCourses(courses, sort, order);
        }

        return courses;
    }

    @Override
    public int findNumberOfRecords(String theme, Integer teacher) {
        if(theme != null) {
            return courseDAO.findNumberOfRecordsByTheme(theme);
        } else if(teacher != null) {
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
    public List<Integer> findUserEnrolled(List<Course> courses) {
        return courseDAO.findUserEnrolled(courses);
    }


}
