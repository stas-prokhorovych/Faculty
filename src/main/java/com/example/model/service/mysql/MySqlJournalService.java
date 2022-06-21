package com.example.model.service.mysql;

import com.example.model.dao.JournalDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.Course;
import com.example.model.entity.Journal;
import com.example.model.service.JournalService;
import com.example.model.service.exception.ServiceException;

import java.util.List;

public class MySqlJournalService implements JournalService {
    private static JournalDAO journalDAO;
    private static DaoFactory daoFactory;
    private static MySqlJournalService instance;

    private MySqlJournalService() {
        try{
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            journalDAO = daoFactory.getJournalDAO();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static JournalService getInstance() {
        if(instance == null) {
            instance = new MySqlJournalService();
        }
        return instance;
    }

    @Override
    public void endCourse(String courseId, String[] studentIds, String[] studentMarks) throws ServiceException {
        try {
            journalDAO.endCourse(courseId, studentIds, studentMarks);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Journal> findJournalInfo(List<Course> finishedCourses, Integer studentId) throws ServiceException {
        try {
            return journalDAO.findJournalInfo(finishedCourses, studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
