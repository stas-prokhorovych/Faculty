package com.example.controller.command;

import com.example.controller.command.impl.get.ErrorPageCommand;
import com.example.controller.command.impl.get.LoginPageCommand;
import com.example.controller.command.impl.get.UsersPageCommand;

public enum CommandEnum {
    LOGIN_PAGE(new LoginPageCommand()),
    USERS_PAGE(new UsersPageCommand()),
    ERROR_PAGE(new ErrorPageCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
