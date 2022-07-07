package com.example.model.service.mysql;

import com.example.model.dao.JournalDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.Course;
import com.example.model.entity.Journal;
import com.example.model.service.JournalService;
import com.example.model.service.exception.ServiceException;
import com.example.model.constants.Mark;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Stream;

public class MySqlJournalService implements JournalService {
    private static final Logger LOG = LogManager.getLogger(MySqlJournalService.class);

    private static JournalDAO journalDAO;
    private static DaoFactory daoFactory;
    private static MySqlJournalService instance;

    private MySqlJournalService() {
        try {
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            journalDAO = daoFactory.getJournalDAO();
        } catch (IllegalArgumentException e) {
            LOG.error("Cannot get journal dao factory", e);
            e.printStackTrace();
        }
    }

    public static JournalService getInstance() {
        if (instance == null) {
            instance = new MySqlJournalService();
        }
        return instance;
    }

    /**
     * End this course
     *
     * @param courseId id course to finish
     * @param studentIds student ids of this course
     * @param studentMarks marks that correspond to students ids
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void endCourse(String courseId, String[] studentIds, String[] studentMarks) throws ServiceException {
        int[] marks = Stream
                .of(studentMarks)
                .mapToInt(Integer::parseInt)
                .toArray();
        String[] markCode = new String[marks.length];
        String[] markExplanation = new String[marks.length];

        for (int i = 0; i < marks.length; i++) {
            Mark markInformation;
            if (marks[i] >= 90 && marks[i] <= 100) {
                markInformation = Mark.A;
                markCode[i] = markInformation.getCode();
                markExplanation[i] = markInformation.getExplanation();
            }
            if (marks[i] >= 81 && marks[i] <= 89) {
                markInformation = Mark.B;
                markCode[i] = markInformation.getCode();
                markExplanation[i] = markInformation.getExplanation();
            }
            if (marks[i] >= 75 && marks[i] <= 80) {
                markInformation = Mark.C;
                markCode[i] = markInformation.getCode();
                markExplanation[i] = markInformation.getExplanation();
            }
            if (marks[i] >= 65 && marks[i] <= 74) {
                markInformation = Mark.D;
                markCode[i] = markInformation.getCode();
                markExplanation[i] = markInformation.getExplanation();
            }
            if (marks[i] >= 55 && marks[i] <= 64) {
                markInformation = Mark.E;
                markCode[i] = markInformation.getCode();
                markExplanation[i] = markInformation.getExplanation();
            }
            if (marks[i] >= 30 && marks[i] <= 54) {
                markInformation = Mark.FX;
                markCode[i] = markInformation.getCode();
                markExplanation[i] = markInformation.getExplanation();
            }
            if (marks[i] >= 1 && marks[i] <= 29) {
                markInformation = Mark.F;
                markCode[i] = markInformation.getCode();
                markExplanation[i] = markInformation.getExplanation();
            }
        }

        try {
            journalDAO.endCourse(courseId, studentIds, marks, markCode, markExplanation);
        } catch (DAOException e) {
            LOG.error("Cannot end course", e);
            throw new ServiceException(e);
        }
    }

    /**
     * Information about particular student
     *
     * @param finishedCourses courses that have status finished
     * @param studentId id of student
     * @return list of journal with marks
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<Journal> findJournalInfo(List<Course> finishedCourses, Integer studentId) throws ServiceException {
        try {
            return journalDAO.findJournalInfo(finishedCourses, studentId);
        } catch (DAOException e) {
            LOG.error("Cannot find journal info", e);
            throw new ServiceException(e);
        }
    }

    /**
     *  Change course info
     *  set course status to finished
     *
     * @param courseId id of course to finish
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void endCourse(String courseId) throws ServiceException{
        try {
            journalDAO.endCourse(courseId);
        } catch (DAOException e) {
            LOG.error("Cannot end course", e);
            throw new ServiceException(e);
        }
    }
}
