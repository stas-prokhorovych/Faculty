package com.example.model.service.mysql;

import com.example.model.dao.JournalDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.service.JournalService;

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
    public void endCourse(String courseId, String[] studentIds, String[] studentMarks) {
        journalDAO.endCourse(courseId, studentIds, studentMarks);
    }
}
