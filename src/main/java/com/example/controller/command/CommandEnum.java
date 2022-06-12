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
    COURSE_CATALOGUE(new CourseCatalogueCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
