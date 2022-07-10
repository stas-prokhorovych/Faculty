package com.example.model.service.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.Course;
import com.example.model.service.CourseService;
import com.example.model.service.exception.CourseServiceException;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
import com.example.model.utils.CourseCatalogueInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;

public class MySqlCourseService implements CourseService {
    private static final Logger LOG = LogManager.getLogger(MySqlCourseService.class);

    private static CourseDAO courseDAO;
    private static DaoFactory daoFactory;
    private static MySqlCourseService instance;

    private MySqlCourseService() {
        try {
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            courseDAO = daoFactory.getCourseDAO();
        } catch (IllegalArgumentException e) {
            LOG.error("Cannot get course dao", e);
            e.printStackTrace();
        }
    }

    public static CourseService getInstance() {
        if (instance == null) {
            instance = new MySqlCourseService();
        }
        return instance;
    }

    /**
     * Delete course by course id
     *
     * @param id an if of course to be deleted
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void deleteCourse(int id) throws ServiceException {
        try {
            if(courseDAO.findCourseById(String.valueOf(id)) == null) {
                throw new CourseServiceException("Course already deleted");
            }
        } catch (DAOException e) {
            LOG.error("Course already deleted", e);
            throw new ServiceException(e);
        }

        try {
            courseDAO.deleteCourse(id);
        } catch (DAOException e) {
            LOG.error("Cannot delete course", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Add course
     *
     * @param course course to be added
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void addCourse(Course course) throws ServiceException {
        Course courseWithThisName;
        try {
            courseWithThisName = courseDAO.findCourseByName(course.getName());
        } catch (DAOException e) {
            LOG.error("Cannot find course in add course method", e);
            throw new ServiceException(e);
        }

        if (courseWithThisName != null) {
            throw new CourseServiceException("course with such name already exist");
        }
        if (course.getStartDate().isAfter(course.getEndDate())) {
            throw new CourseServiceException("start date must be before end date");
        }
        if (course.getStartDate().isBefore(LocalDateTime.now().with(next(MONDAY)))) {
            throw new CourseServiceException("start date at least:\n" + LocalDateTime.now().with(next(MONDAY)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }

        try {
            courseDAO.addCourse(course);
        } catch (DAOException e) {
            LOG.error("Cannot add course", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Find distinct themes that exist
     *
     * @return a list of themes
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<String> findThemes() throws ServiceException {
        try {
            return courseDAO.findThemes();
        } catch (DAOException e) {
            LOG.error("Cannot find themes", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Update specified course
     *
     * @param course course to be updated
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void updateCourse(Course course, String previousName) throws ServiceException {
        try {
            Course courseWithThisName;
            courseWithThisName = courseDAO.findCourseByName(course.getName());

            if (!course.getName().equals(previousName) &&  courseWithThisName != null) {
                throw new CourseServiceException("course with such name already exist");
            }

            if (course.getStartDate().isAfter(course.getEndDate())) {
                throw new CourseServiceException("start date must be before end date");
            }
            if (course.getStartDate().isBefore(LocalDateTime.now().with(next(MONDAY)))) {
                throw new CourseServiceException("start date at least:\n" + LocalDateTime.now().with(next(MONDAY)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }

            courseDAO.updateCourse(course);
        } catch (DAOException e) {
            LOG.error("Cannot update course", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Find course with no teacher assigned
     *
     * @param status status of courses
     * @return return all lists with no teachers assigned yet
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<Course> findCoursesNoTeacherAssigned(String status) throws ServiceException {
        try {
            return courseDAO.findCoursesNoTeacherAssigned(status);
        } catch (DAOException e) {
            LOG.error("Cannot find no teacher assigned courses", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Assign course for student
     * after assignment became teacher
     *
     * @param courseId id of course to assign
     * @param studentId student id that became teacher
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void assignCourse(String courseId, String studentId) throws ServiceException {
        try {
            courseDAO.assignCourse(courseId, studentId);
        } catch (DAOException e) {
            LOG.error("Cannot assign course", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Used for journal
     * to show course of teacher by status
     *
     * @param teacherId id of specified logged teacher
     * @param status status of course
     * @return return course with specified teacherId and status
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<Course> findCoursesByTeacherAndStatus(Integer teacherId, String status) throws ServiceException {
        try {
            return courseDAO.findCoursesByTeacherAndStatus(teacherId, status);
        } catch (DAOException e) {
            LOG.error("Cannot find courses by teacher and status", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Used for your courses
     * to show course of student by status
     *
     * @param studentId id of specified logged student
     * @param status status of course
     * @return return course with specified studentId and status
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<Course> findStudentCoursesByStatus(Integer studentId, String status) throws ServiceException {
        try {
            return courseDAO.findStudentCoursesByStatus(studentId, status);
        } catch (DAOException e) {
            LOG.error("Cannot find courses by student and status", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Start course
     * change date to current
     *
     * @param status new status of the course
     * @param courseId id of specified course
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void startCourse(String status, Integer courseId) throws ServiceException {
        try {
            if(courseDAO.courseAlreadyStart("In progress", courseId)) {
                throw new CourseServiceException("Course already started");
            }
        } catch (DAOException e) {
            LOG.error("Cannot check course already started", e);
            throw new ServiceException(e);
        }

        try {
            courseDAO.startCourse(status, courseId);
        } catch (DAOException e) {
            LOG.error("Cannot start course", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Find course by id
     *
     * @param id id of course
     * @return course with given id
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public Course findCourseById(String id) throws ServiceException {
        try {
            return courseDAO.findCourseById(id);
        } catch (DAOException e) {
            LOG.error("Cannot find courses by id", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Used for pagination
     *
     * @param offset offset for data, by page
     * @param recordsPerPage record per page
     * @param role user type(Admin, Teacher, Student, Guest)
     * @param theme theme if specified
     * @param teacherId specified teacher if selected
     * @param sort sort parameter if selected
     * @param order order of sorting if selected
     * @return return CourseCatalogueInfo consist of Courses, Teachers, Student Enrolled
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String role, String theme, Integer teacherId, String sort, String order) throws ServiceException {
        try {
            return courseDAO.findCoursesByPage(offset, recordsPerPage, role, theme, teacherId, sort, order);
        } catch (DAOException e) {
            LOG.error("Cannot find courses by page", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Used for getting number of records
     * for displaying pagination buttons
     *
     * @param role role of user Student, Teacher, Admin, Guest
     * @param theme theme of course if selected
     * @param teacherId specified teacher if selected
     * @return number of records for specified parameters
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public int numberOfRecords(String role, String theme, Integer teacherId) throws ServiceException {
        try {
            return courseDAO.numberOfRecords(role, theme, teacherId);
        } catch (DAOException e) {
            LOG.error("Cannot find number of records", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Check if each course selected
     * used for Student role
     * to show enroll/leave option
     *
     * @param courses list of courses by page
     * @param studentId id of logged student
     * @return return true if course selected or false otherwise
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<Boolean> courseAlreadySelected(List<Course> courses, Integer studentId) throws ServiceException {
        try {
            return courseDAO.courseAlreadySelected(courses, studentId);
        } catch (DAOException e) {
            LOG.error("Cannot find courses already selected", e);
            throw new ServiceException(e);
        }
    }
}
