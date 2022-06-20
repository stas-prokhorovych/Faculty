package com.example.model.dao.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.utils.pagination.CourseCatalogueInfo;
import com.example.model.utils.pagination.PaginationQueue;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.model.constants.Query.*;

public class MySQLCourseDAO implements CourseDAO {
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

    public int findNumberOfRecords() {
        int numberOfRecords = 0;
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_NUMBER_OF_RECORDS)) {
            if (rs.next()) {
                numberOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberOfRecords;
    }

    public int findNumberOfRecordsByTheme(String theme) {
        int numberOfRecords = 0;
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_NUMBER_OF_RECORDS_BY_THEME);
        ) {
            statement.setString(1, theme);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    numberOfRecords = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberOfRecords;
    }

    public void deleteCourse(int id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(DELETE_COURSE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCourse(Course course) {
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
            statement.setString(i++, course.getCourseStatus().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCourse(String name, int id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findThemes() {
        List<String> themes = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_THEMES)) {
            while (rs.next()) {
                themes.add(rs.getString("theme"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return themes;
    }

    @Override
    public List<Course> getNoTeacherCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_NO_TEACHER_COURSES)) {
            while (rs.next()) {
                Course course = mapResultSet(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public CourseCatalogueInfo findCoursesByPage(int offset, int recordsPerPage, String type, String theme, Integer teacherId, String sort, String order) {
        List<Course> courses = new LinkedList<>();
        List<User> teachers = new LinkedList<>();
        List<Integer> studentsEnrolled = new LinkedList<>();
        String queue = PaginationQueue.makeQueue(type, theme, teacherId, sort, order);

        CourseCatalogueInfo catalogueInfo = new CourseCatalogueInfo();

        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(queue)) {
            int i = 1;
            if (type != null) {
                statement.setString(i++, type);
            }
            if (theme != null) {
                statement.setString(i++, theme);
            }
            if (teacherId != null) {
                statement.setInt(i++, teacherId);
            }

            statement.setInt(i++, offset);
            statement.setInt(i++, recordsPerPage);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {

                    Course course = mapResultSet(rs);
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
            throw new RuntimeException(e);
        }
        return catalogueInfo;
    }

    @Override
    public int findNumberOfRecordsByTeacher(Integer teacher) {
        int numberOfRecords = 0;
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_NUMBER_OF_RECORDS_BY_TEACHER);
        ) {
            statement.setInt(1, teacher);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    numberOfRecords = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberOfRecords;
    }

    @Override
    public List<Course> findAllInProgressCoursesByTeacherId(Integer teacherId) {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_ALL_IN_PROGRESS_COURSES_BY_TEACHER_ID);
        ) {
            statement.setInt(1, teacherId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSet(rs);
                    courses.add(course);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public List<Course> findFinishedCoursesByStudentId(Integer studentId) {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SELECT_STUDENT_FINISHED_COURSES);
        ) {
            statement.setInt(1, studentId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSet(rs);
                    courses.add(course);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public Course findCourseById(String id) {
        Course course = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_ALL_COURSE_BY_ID)) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    course = mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    @Override
    public Course findCourseByName(String name) {
        Course course = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_COURSE_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    course = mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    @Override
    public List<Course> findAllFinishedCoursesByTeacherId(int teacherId) {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_ALL_FINISHED_COURSES_BY_TEACHER_ID);
        ) {
            statement.setInt(1, teacherId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSet(rs);
                    courses.add(course);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public List<Course> findRegisteredCoursesByStudentId(Integer studentId) {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SELECT_STUDENT_REGISTERED_COURSES);
        ) {
            statement.setInt(1, studentId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSet(rs);
                    courses.add(course);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public List<Course> findInProgressCoursesByStudentId(Integer studentId) {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SELECT_STUDENT_IN_PROGRESS_COURSES);
        ) {
            statement.setInt(1, studentId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSet(rs);
                    courses.add(course);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    private Course mapResultSet(ResultSet rs) {
        Course course;
        try {
            course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setTheme(rs.getString("theme"));
            course.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
            course.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
            course.setLecturer(rs.getInt("id_lecturer"));
            course.setCourseStatus(Course.CourseStatus.OPENED);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }
}