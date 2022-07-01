package com.example.model.dao.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.GenericDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.utils.CourseCatalogueInfo;
import com.example.model.utils.PaginationQueue;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.model.constants.Query.*;

public class MySQLCourseDAO extends GenericDAO<Course> implements CourseDAO {
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

    public void deleteCourse(int id) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(DELETE_COURSE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

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
            throw new DAOException(e);
        }
    }

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
            throw new DAOException(e);
        }
    }

    public List<String> findThemes() throws DAOException {
        List<String> themes = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_THEMES)) {
            while (rs.next()) {
                themes.add(rs.getString("theme"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return themes;
    }

    @Override
    public List<Course> getNoTeacherCourses() throws DAOException {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_NO_TEACHER_COURSES)) {
            while (rs.next()) {
                Course course = mapToEntity(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return courses;
    }

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
            throw new DAOException(e);
        }
        return catalogueInfo;
    }

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
                statement.setInt(i++, teacherId);
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    numberOfRecords = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return numberOfRecords;
    }

    @Override
    public void startCourse(Integer courseId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(START_COURSE)
        ) {
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(2, courseId);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void assignCourse(String courseId, String studentId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(ASSIGN_COURSE)) {
            statement.setString(1, studentId);
            statement.setString(2, "Opened for registration");
            statement.setString(3, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

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
            throw new DAOException(e);
        }
        return courseAlreadySelected;
    }

    @Override
    public Course findCourseById(String id) throws DAOException {
        return findEntityByField(FIND_ALL_COURSE_BY_ID, id);
    }

    @Override
    public Course findCourseByName(String name) throws DAOException {
        return findEntityByField(FIND_COURSE_BY_NAME, name);

    }

    @Override
    public List<Course> finAllOpenForRegCoursesByTeacherId(Integer teacherId) throws DAOException {
        return findEntitiesByField(FIND_ALL_OPEN_COURSES_BY_TEACHER_ID, teacherId);
    }

    @Override
    public List<Course> findAllInProgressCoursesByTeacherId(Integer teacherId) throws DAOException {
        return findEntitiesByField(FIND_ALL_IN_PROGRESS_COURSES_BY_TEACHER_ID, teacherId);
    }

    @Override
    public List<Course> findFinishedCoursesByStudentId(Integer studentId) throws DAOException {
        return findEntitiesByField(SELECT_STUDENT_FINISHED_COURSES, studentId);
    }

    @Override
    public List<Course> findAllFinishedCoursesByTeacherId(int teacherId) throws DAOException {
        return findEntitiesByField(FIND_ALL_FINISHED_COURSES_BY_TEACHER_ID, teacherId);
    }

    @Override
    public List<Course> findRegisteredCoursesByStudentId(Integer studentId) throws DAOException {
        return findEntitiesByField(SELECT_STUDENT_REGISTERED_COURSES, studentId);

    }

    @Override
    public List<Course> findInProgressCoursesByStudentId(Integer studentId) throws DAOException {
        return findEntitiesByField(SELECT_STUDENT_IN_PROGRESS_COURSES, studentId);
    }

    @Override
    protected Course mapToEntity(ResultSet rs) throws DAOException {
        Course course;
        try {
            course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setTheme(rs.getString("theme"));
            course.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
            course.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
            course.setLecturer(rs.getInt("id_lecturer"));
            String courseStatus = rs.getString("course_status");
            switch (courseStatus) {
                case "Opened for registration":
                    course.setCourseStatus(Course.CourseStatus.OPENED);
                    break;
                case "In progress":
                    course.setCourseStatus(Course.CourseStatus.IN_PROGRESS);
                    break;
                case "Finished":
                    course.setCourseStatus(Course.CourseStatus.FINISHED);
                    break;
                default:
                    course.setCourseStatus(Course.CourseStatus.CLOSED);
                    break;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return course;
    }
}