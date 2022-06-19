package com.example.model.dao.mysql;

import com.example.model.dao.JournalDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.Course;

import java.sql.*;

import static com.example.model.constants.Query.*;

public class MySQLJournalDAO implements JournalDAO {
    private static MySQLJournalDAO instance;
    private static DaoFactory daoFactory;

    static {
        daoFactory = DaoFactory.getDaoFactory("MYSQL");
    }

    public static JournalDAO getInstance() {
        if (instance == null) {
            instance = new MySQLJournalDAO();
        }
        return instance;
    }

    @Override
    public void endCourse(String courseId, String[] studentIds, String[] studentMarks) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(WRITE_MARKS_TO_JOURNAL, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            for (int i = 0; i < studentIds.length; i++) {
                int idStudentOnCourse = findIdOfStudentOnCourse(courseId, studentIds[i]);

                statement.setInt(1, idStudentOnCourse);
                statement.setString(2, studentMarks[i]);
                statement.executeUpdate();
            }
            changeCourseInfo(courseId);
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeCourseInfo(String courseId) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_COURSE_INFO_TO_FINISH)) {
            statement.setString(1, "Finished");
            statement.setString(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int findIdOfStudentOnCourse(String courseId, String studentId) {
        int id = 0;
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_ID_STUDENT_ON_COURSE)) {
            statement.setString(1, studentId);
            statement.setString(2, courseId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
