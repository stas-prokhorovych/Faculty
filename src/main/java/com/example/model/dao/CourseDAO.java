package com.example.model.dao;

import com.example.model.db.DataSource;
import com.example.model.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.model.query.Query.*;


public class CourseDAO {
    public static List<Course> findAllCoursesByPage(int offset, int noOfRecords) {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SELECT_COURSES_BY_THEME_LIMIT)) {
            statement.setInt(1, offset);
            statement.setInt(2, noOfRecords);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setName(rs.getString("name"));
                    course.setTheme(rs.getString("theme"));
                    course.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                    course.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
                    course.setLecturer(rs.getInt("id_lecturer"));
                    course.setCourseStatus(Course.CourseStatus.CLOSED);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    public static int findNumberOfRecords() {
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


    public static List<Course> findAllCoursesByTheme(String theme) {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SELECT_COURSES_BY_THEME)) {
            statement.setString(1, theme);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setName(rs.getString("name"));
                    course.setTheme(rs.getString("theme"));
                    course.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                    course.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
                    course.setLecturer(rs.getInt("id_lecturer"));
                    course.setCourseStatus(Course.CourseStatus.CLOSED);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    public static void deleteCourse(int id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(DELETE_COURSE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addCourse(Course course) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(CREATE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getTheme());
            statement.setTimestamp(3, Timestamp.valueOf(course.getStartDate()));
            statement.setTimestamp(4, Timestamp.valueOf(course.getEndDate()));
            statement.setInt(5, course.getLecturer());
            statement.setString(6, course.getCourseStatus().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCourse(String name, int id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findName(int id) {
        String name = "";
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_COURSE_BY_ID, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("name");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    public static List<String> findThemes() {
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

//    public  static List<Course> findCourseByTeacherID(int id) {
//
//    }
}
