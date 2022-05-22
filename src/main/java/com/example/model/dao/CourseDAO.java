package com.example.model.dao;

import com.example.model.db.DataSource;
import com.example.model.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private static final String SQL_FIND_ALL_COURSES = "SELECT * FROM course";
    private static final String SQL_ADD_COURSE = "INSERT INTO course(name, theme, duration_in_days, id_lecturer) VALUES(?,?,?,?)";
    private static final String SQL_DELETE_COURSE = "DELETE FROM course WHERE id=?";
    private static final String SQL_UPDATE_COURSE = "UPDATE course SET name=? WHERE id=?";
    private static final String SQL_FIND_COURSE_NAME = "SELECT name FROM course WHERE id=?";

    public static List<Course> findAllCourses() {
        List<Course> courses = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(SQL_FIND_ALL_COURSES)) {
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setTheme(rs.getString("theme"));
                course.setDurationInDays(rs.getInt("duration_in_days"));
                course.setLecturer(rs.getInt("id_lecturer"));
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    public static void deleteCourse(int id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_DELETE_COURSE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addCourse(Course course) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_ADD_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getTheme());
            statement.setInt(3, course.getDurationInDays());
            statement.setInt(4, course.getLecturer());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCourse(String name, int id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
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
             PreparedStatement statement = con.prepareStatement(SQL_FIND_COURSE_NAME, Statement.RETURN_GENERATED_KEYS)) {
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
}
