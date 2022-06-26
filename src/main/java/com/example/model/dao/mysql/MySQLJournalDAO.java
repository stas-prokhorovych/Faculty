package com.example.model.dao.mysql;

import com.example.model.dao.GenericDAO;
import com.example.model.dao.JournalDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.Course;
import com.example.model.entity.Journal;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.model.constants.Query.*;

public class MySQLJournalDAO extends GenericDAO<Journal> implements JournalDAO {
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
    public void endCourse(String courseId, String[] studentIds, int[] studentMarks, String[] markCode, String[] markExplanation) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(WRITE_MARKS_TO_JOURNAL, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            for (int i = 0; i < studentIds.length; i++) {
                int idStudentOnCourse = findIdOfStudentOnCourse(courseId, studentIds[i]);

                statement.setInt(1, idStudentOnCourse);
                statement.setInt(2, studentMarks[i]);
                statement.setString(3, markCode[i]);
                statement.setString(4, markExplanation[i]);
                statement.executeUpdate();
            }
            changeCourseInfo(courseId);
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException(e);
        }
    }

    @Override
    public List<Journal> findJournalInfo(List<Course> finishedCourses, Integer studentId) throws DAOException {
        List<Journal> journalInfo = new ArrayList<>();

        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_JOURNAL_INFO, Statement.RETURN_GENERATED_KEYS)) {
            for (Course course : finishedCourses) {
                statement.setInt(1, studentId);
                statement.setInt(2, course.getId());
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        Journal journal = mapToEntity(rs);
                        journalInfo.add(journal);
                    }
                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return journalInfo;
    }


    private void changeCourseInfo(String courseId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_COURSE_INFO_TO_FINISH)) {
            statement.setString(1, "Finished");
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(3, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private int findIdOfStudentOnCourse(String courseId, String studentId) throws DAOException {
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
            throw new DAOException(e);
        }
        return id;
    }

    @Override
    protected Journal mapToEntity(ResultSet rs) throws DAOException {
        Journal journal;
        try {
            journal = new Journal();
            journal.setId(rs.getInt("id"));
            journal.setIdStudentCourse(rs.getInt("id_student_course"));
            journal.setMarkPoints(rs.getInt("mark_points"));
            journal.setMarkCode(rs.getString("mark_code"));
            journal.setMarkExplanation(rs.getString("mark_explanation"));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return journal;
    }
}
