package com.example.model.service.factory;

import com.example.model.dao.mysql.factory.MySqlDaoFactory;
import com.example.model.service.CourseService;
import com.example.model.service.JournalService;
import com.example.model.service.UserService;
import com.example.model.service.mysql.factory.MySqlServiceFactory;

public abstract class ServiceFactory {
    public static ServiceFactory getServiceFactory (String db) {
        switch (db) {
            case "MYSQL":
                return MySqlServiceFactory.getInstance();
            default:
                throw new IllegalArgumentException();
        }
    }

    public abstract CourseService getCourseService();

    public abstract JournalService getJournalService();

    public abstract UserService getUserService();
}
