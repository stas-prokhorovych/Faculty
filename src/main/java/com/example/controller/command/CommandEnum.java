package com.example.controller.command;

import com.example.controller.command.impl.*;

public enum CommandEnum {
    UNKNOWN(new UnknownCommand()),
    LOGOUT(new LogoutCommand()),
    LOGIN(new LoginCommand()),
    CREATE_COURSE(new CreateCourseCommand()),
    REGISTER(new RegistrationCommand()),
    DELETE_COURSE(new DeleteCourseCommand()),
    UPDATE_COURSE(new UpdateCourseCommand()),
    COURSE_CATALOGUE(new CourseCatalogueCommand()),
    USER_CATALOGUE(new UserCatalogueCommand()),
    ADD_TEACHER(new AddTeacherCommand()),
    ENROLL_ON_COURSE(new EnrollOnCourse());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
