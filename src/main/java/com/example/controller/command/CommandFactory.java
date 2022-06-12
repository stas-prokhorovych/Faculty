package com.example.controller.command;

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
                iCommand = CommandEnum.UNKNOWN.getCommand();
            }
        } else {
            iCommand = CommandEnum.UNKNOWN.getCommand();
        }

        return iCommand;
    }
}
