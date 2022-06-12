package com.example.model.service.mysql.factory;

import com.example.model.service.CourseService;
import com.example.model.service.JournalService;
import com.example.model.service.UserService;
import com.example.model.service.factory.ServiceFactory;
import com.example.model.service.mysql.MySqlCourseService;
import com.example.model.service.mysql.MySqlJournalService;
import com.example.model.service.mysql.MySqlUserService;

public class MySqlServiceFactory extends ServiceFactory {
    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if(instance == null) {
            instance = new MySqlServiceFactory();
        }
        return instance;
    }

    @Override
    public CourseService getCourseService() {
        return MySqlCourseService.getInstance();
    }

    @Override
    public JournalService getJournalService() {
        return MySqlJournalService.getInstance();
    }

    @Override
    public UserService getUserService() {
        return MySqlUserService.getInstance();
    }
}
