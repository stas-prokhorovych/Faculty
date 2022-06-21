package com.example.model.service.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.Course;
import com.example.model.service.exception.CourseServiceException;
import com.example.model.service.CourseService;
import com.example.model.service.exception.ServiceException;
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
    public void deleteCourse(int id) throws ServiceException {
        try {
            courseDAO.deleteCourse(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCourse(Course course) throws ServiceException {
        Course courseWithThisName;
        try {
            courseWithThisName = courseDAO.findCourseByName(course.getName());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (courseWithThisName != null) {
            throw new CourseServiceException("course with such name already exist");
        }
        if (course.getStartDate().isAfter(course.getEndDate())) {
            throw new CourseServiceException("start date must be before end date");
        }

        try {
            courseDAO.addCourse(course);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<String> findThemes() throws ServiceException {
        try {
            return courseDAO.findThemes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCourse(String name, int id) throws ServiceException {
        try {
            courseDAO.updateCourse(name, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> getNoTeacherCourses() throws ServiceException {
        try {
            return courseDAO.getNoTeacherCourses();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<Course> findAllFinishedCoursesByTeacherId(int teacherId) throws ServiceException {
        try {
            return courseDAO.findAllFinishedCoursesByTeacherId(teacherId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findRegisteredCoursesByStudentId(Integer studentId) throws ServiceException {
        try {
            return courseDAO.findRegisteredCoursesByStudentId(studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findInProgressCoursesByStudentId(Integer studentId) throws ServiceException {
        try {
            return courseDAO.findInProgressCoursesByStudentId(studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findAllInProgressCoursesByTeacherId(Integer teacherId) throws ServiceException {
        try {
            return courseDAO.findAllInProgressCoursesByTeacherId(teacherId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findFinishedCoursesByStudentId(Integer studentId) throws ServiceException {
        try {
            return courseDAO.findFinishedCoursesByStudentId(studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Course findCourseById(String id) throws ServiceException {
        try {
            return courseDAO.findCourseById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String type, String theme, Integer teacherId, String sort, String order) throws ServiceException {
        try {
            return courseDAO.findCoursesByPage(offset, recordsPerPage, type, theme, teacherId, sort, order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int numberOfRecords(String role, String theme, Integer teacherId) throws ServiceException {
        try {
            return courseDAO.numberOfRecords(role, theme, teacherId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Boolean> courseAlreadySelected(List<Course> courses, Integer studentId) throws ServiceException {
        try {
            return courseDAO.courseAlreadySelected(courses, studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
