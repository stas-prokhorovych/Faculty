package com.example.model.dao.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.utils.CourseCatalogueInfo;
import com.example.model.utils.PaginationQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.model.constants.Query.*;

public class MySQLCourseDAO extends MySQLGenericDAO<Course> implements CourseDAO {
    private static final Logger LOG = LogManager.getLogger(MySQLCourseDAO.class);

    private static MySQLCourseDAO instance;
    private static DaoFactory daoFactory;

    static {
        daoFactory = DaoFactory.getDaoFactory("MYSQL");
    }

    public static CourseDAO getInstance() {
        if (instance == null) {
            instance = new MySQLCourseDAO();
        }
        return instance;
    }

    /**
     * Delete course by course id
     *
     * @param id an if of course to be deleted
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    public void deleteCourse(int id) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(DELETE_COURSE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOG.error("Unable to delete course", e);
            throw new DAOException(e);
        }
    }

    /**
     * Add course
     *
     * @param course course to be added
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    public void addCourse(Course course) throws DAOException {
        String query;

        if (course.getLecturer() == 0) {
            query = CREATE_COURSE_NO_TEACHER;
        } else {
            query = CREATE_COURSE_WITH_TEACHER;
        }

        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setString(i++, course.getName());
            statement.setString(i++, course.getTheme());
            statement.setTimestamp(i++, Timestamp.valueOf(course.getStartDate()));
            statement.setTimestamp(i++, Timestamp.valueOf(course.getEndDate()));
            if (course.getLecturer() != 0) {
                statement.setInt(i++, course.getLecturer());
            }
            statement.setString(i, course.getCourseStatus().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Unable to add course", e);
            throw new DAOException(e);
        }
    }

    /**
     * Update specified course
     *
     * @param course course to be updated
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    public void updateCourse(Course course) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getTheme());
            statement.setTimestamp(3, Timestamp.valueOf(course.getStartDate()));
            statement.setTimestamp(4, Timestamp.valueOf(course.getEndDate()));
            statement.setInt(5, course.getLecturer());
            statement.setInt(6, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Unable to update course", e);
            throw new DAOException(e);
        }
    }

    /**
     * Find distinct themes that exist
     *
     * @return a list of themes
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    public List<String> findThemes() throws DAOException {
        List<String> themes = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_THEMES)) {
            while (rs.next()) {
                themes.add(rs.getString("theme"));
            }
        } catch (SQLException e) {
            LOG.error("Unable to find themes", e);
            throw new DAOException(e);
        }
        return themes;
    }

    /**
     * Find course with no teacher assigned
     *
     * @param status status of courses
     * @return return all lists with no teachers assigned yet
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<Course> findCoursesNoTeacherAssigned(String status) throws DAOException {
        return findEntitiesByField(FIND_NO_TEACHER_COURSES, status);
    }

    /**
     * Used for pagination
     *
     * @param offset offset for data, by page
     * @param recordsPerPage record per page
     * @param role user role(Admin, Teacher, Student, Guest)
     * @param theme theme if specified
     * @param teacherId specified teacher if selected
     * @param sort sort parameter if selected
     * @param order order of sorting if selected
     * @return return CourseCatalogueInfo consist of Courses, Teachers, Student Enrolled
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String role, String theme, Integer teacherId, String sort, String order) throws DAOException {
        List<Course> courses = new LinkedList<>();
        List<User> teachers = new LinkedList<>();
        List<Integer> studentsEnrolled = new LinkedList<>();
        String queue = PaginationQueue.makeQueue(role, theme, teacherId, sort, order);

        CourseCatalogueInfo catalogueInfo = new CourseCatalogueInfo();

        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(queue)) {
            int i = 1;
            if (theme != null) {
                statement.setString(i++, theme);
            }
            if (teacherId != null) {
                statement.setInt(i++, teacherId);
            }

            statement.setInt(i++, offset);
            statement.setInt(i, recordsPerPage);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {

                    Course course = mapToEntity(rs);
                    courses.add(course);

                    User teacher = new User();
                    teacher.setId(rs.getInt("id"));
                    teacher.setLogin(rs.getString("login"));
                    teacher.setPassword(rs.getString("password"));
                    teacher.setEmail(rs.getString("email"));

                    if (rs.getString("role").equals("Admin")) {
                        teacher.setRole(User.Role.ADMIN);
                    } else if (rs.getString("role").equals("Teacher")) {
                        teacher.setRole(User.Role.TEACHER);
                    } else {
                        teacher.setRole(User.Role.STUDENT);
                    }
                    teacher.setFirstName(rs.getString("first_name"));
                    teacher.setLastName(rs.getString("last_name"));
                    teacher.setPhoneNumber(rs.getString("phone_number"));
                    teacher.setUserAccess(rs.getBoolean("user_access"));
                    teachers.add(teacher);

                    studentsEnrolled.add(rs.getInt("student_enrolled"));
                }
            }
            catalogueInfo.setCourses(courses);
            catalogueInfo.setTeachers(teachers);
            catalogueInfo.setStudentsEnrolled(studentsEnrolled);
        } catch (SQLException e) {
            LOG.error("Unable to find courses by page", e);
            throw new DAOException(e);
        }
        return catalogueInfo;
    }

    /**
     * Used for getting number of records
     * for displaying pagination buttons
     *
     * @param role role of user Student, Teacher, Admin, Guest
     * @param theme theme of course if selected
     * @param teacherId specified teacher if selected
     * @return number of records for specified parameters
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public int numberOfRecords(String role, String theme, Integer teacherId) throws DAOException {
        int numberOfRecords = 0;
        String queue = PaginationQueue.numberOfPages(role, theme, teacherId);

        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(queue)
        ) {
            int i = 1;
            if (theme != null) {
                statement.setString(i++, theme);
            }
            if (teacherId != null) {
                statement.setInt(i, teacherId);
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    numberOfRecords = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            LOG.error("Unable to find number of records", e);
            throw new DAOException(e);
        }
        return numberOfRecords;
    }

    /**
     * Start course
     * change date to current
     *
     * @param status new status of the course
     * @param courseId id of specified course
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public void startCourse(String status, Integer courseId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(START_COURSE)
        ) {
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(2, status);
            statement.setInt(3, courseId);
            statement.execute();
        } catch (SQLException e) {
            LOG.error("Unable to start a course", e);
            throw new DAOException(e);
        }
    }

    /**
     * Assign course for student
     * after assignment became teacher
     *
     * @param courseId id of course to assign
     * @param studentId student id that became teacher
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public void assignCourse(String courseId, String studentId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(ASSIGN_COURSE)) {
            statement.setString(1, studentId);
            statement.setString(2, "Opened for registration");
            statement.setString(3, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Unable to assign a course", e);
            throw new DAOException(e);
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
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<Boolean> courseAlreadySelected(List<Course> courses, Integer studentId) throws DAOException {
        List<Boolean> courseAlreadySelected = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_SELECTED_COURSE)
        ) {
            for (Course course : courses) {
                statement.setInt(1, studentId);
                statement.setInt(2, course.getId());
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        courseAlreadySelected.add(true);
                    } else {
                        courseAlreadySelected.add(false);
                    }
                }
            }

        } catch (SQLException e) {
            LOG.error("Unable to check if courses selected", e);
            throw new DAOException(e);
        }
        return courseAlreadySelected;
    }

    /**
     * Find course by id
     *
     * @param id id of course
     * @return course with given id
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public Course findCourseById(String id) throws DAOException {
        return findEntityByField(FIND_ALL_COURSE_BY_ID, id);
    }

    /**
     * Find course by name
     *
     * @param name name of the course
     * @return course with give name
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public Course findCourseByName(String name) throws DAOException {
        return findEntityByField(FIND_COURSE_BY_NAME, name);
    }

    /**
     * Used for journal
     * to show course of teacher by status
     *
     * @param teacherId id of specified logged teacher
     * @param status status of course
     * @return return course with specified teacherId and status
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<Course> findCoursesByTeacherAndStatus(Integer teacherId, String status) throws DAOException {
        return findEntitiesByField(FIND_COURSES_BY_TEACHER_AND_STATUS, teacherId, status);
    }

    /**
     * Used for your courses
     * to show course of student by status
     *
     * @param studentId id of specified logged student
     * @param status status of course
     * @return return course with specified studentId and status
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<Course> findStudentCoursesByStatus(Integer studentId, String status) throws DAOException {
        return findEntitiesByField(SELECT_STUDENT_COURSES_BY_STATUS, status, studentId);
    }

    /**
     * Used to map object
     *
     * @param rs rs of give executed statement
     * @return  return mapped object
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public Course mapToEntity(ResultSet rs) throws DAOException {
        Course course;
        try {
            String givenStatus = rs.getString("course_status");
            Course.CourseStatus status;
            switch (givenStatus) {
                case "Opened for registration":
                    status = Course.CourseStatus.OPENED;
                    break;
                case "In progress":
                    status = Course.CourseStatus.IN_PROGRESS;
                    break;
                case "Finished":
                    status = Course.CourseStatus.FINISHED;
                    break;
                default:
                    status = Course.CourseStatus.CLOSED;
                    break;
            }

            course = new Course.Builder()
                    .setId(rs.getInt("id"))
                    .setName(rs.getString("name"))
                    .setTheme(rs.getString("theme"))
                    .setStartDate(rs.getTimestamp("start_date").toLocalDateTime())
                    .setEndDate(rs.getTimestamp("end_date").toLocalDateTime())
                    .setLecturer(rs.getInt("id_lecturer"))
                    .setCourseStatus(status)
                    .build();

        } catch (SQLException e) {
            LOG.error("Unable to map to Course", e);
            throw new DAOException(e);
        }
        return course;
    }
}