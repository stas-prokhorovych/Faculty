package com.example.model.dao.mysql;

import com.example.model.dao.JournalDAO;
import com.example.model.dao.factory.DaoFactory;

public class MySQLJournalDAO implements JournalDAO {
    private static MySQLJournalDAO instance;
    private static DaoFactory daoFactory;

    static {
        daoFactory = DaoFactory.getDaoFactory("MYSQL");
    }
    public static JournalDAO getInstance() {
        if(instance == null) {
            instance = new MySQLJournalDAO();
        }
        return instance;
    }
}
