package com.example.controller.command;

import com.example.controller.command.impl.*;
import com.example.controller.command.impl.page.*;

/**
 * Contains and gives all commands in system.
 */
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
    ENROLL_ON_COURSE(new EnrollOnCourseCommand()),
    LEAVE_COURSE(new LeaveCourseCommand()),
    SHOW_JOURNAL(new ShowJournalCommand()),
    PDF_REPORT(new PdfReportCommand()),
    YOUR_COURSES(new StudentCoursesCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),
    SHOW_GRADUATES(new ShowGraduatesCommand()),
    END_COURSE(new EndCourseCommand()),
    SHOW_TEACHERS(new ShowTeachersCommand()),
    START_COURSE(new StartCourseCommand()),
    CREATE_TEACHER(new CreateTeacherCommand()),
    ASSIGN_TEACHER_TO_COURSE(new AssignTeacherToCourseCommand()),
    SHOW_COURSE_INFO(new ShowCourseInfoCommand()),
    GOTO_HOME_PAGE(new GoToHomePageCommand()),
    GOTO_LOGIN_PAGE(new GoToLoginPageCommand()),
    GOTO_SIGNUP_PAGE(new GoToRegisterCommand()),
    GOTO_UPDATE_COURSE_PAGE(new GoToUpdateCoursePageCommand()),
    GOTO_GRADUATES_PAGE(new GoToGraduatesPageCommand()),
    GOTO_PROFILE_PAGE(new GoToProfileCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
