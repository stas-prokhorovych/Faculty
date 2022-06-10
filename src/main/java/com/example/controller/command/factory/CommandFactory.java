package com.example.controller.command.factory;

import com.example.controller.command.CommandEnum;
import com.example.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public final class CommandFactory {
    private CommandFactory() {
    }

    public static Command getCommand(HttpServletRequest request) {
        String command = request.getParameter("command");
        Command iCommand = null;

        if (command != null) {
            try {
                iCommand = CommandEnum.valueOf(command).getCommand();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                iCommand = CommandEnum.ERROR_PAGE.getCommand();
            }
        } else {
            iCommand = CommandEnum.ERROR_PAGE.getCommand();
        }

        return iCommand;
    }
}
