package com.example.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public final class CommandFactory {
    private static final Logger LOG = LogManager.getLogger(CommandFactory.class);

    private CommandFactory() {
    }

    /**
     * Defines Concrete {@link Command} implementation based on
     * {@link CommandEnum} used for mapping
     *
     * @param request request for concrete command implementation
     * @return Concrete command implementation
     */
    public static Command getCommand(HttpServletRequest request) {
        String command = request.getParameter("command");
        Command iCommand = null;

        if (command != null) {
            try {
                iCommand = CommandEnum.valueOf(command).getCommand();
            } catch (IllegalArgumentException e) {
                LOG.warn("Command with specified name not found");
                iCommand = CommandEnum.UNKNOWN.getCommand();
            }
        } else {
            LOG.warn("Command parameter is empty");
            iCommand = CommandEnum.UNKNOWN.getCommand();
        }

        return iCommand;
    }
}
