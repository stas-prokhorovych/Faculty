package com.example.model.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Course {
    private int id;
    private String name;
    private String theme;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long durationInDays;
    private int lecturer;
    private CourseStatus courseStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public long getDurationInDays() {
        if(getStartDate() != null && getEndDate() != null) {
            durationInDays = ChronoUnit.DAYS.between(startDate, endDate);
        }
        return durationInDays;
    }

    public int getLecturer() {
        return lecturer;
    }

    public void setLecturer(int lecturer) {
        this.lecturer = lecturer;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public enum CourseStatus {
        OPENED("Opened"),
        IN_PROGRESS("In progress"),
        CLOSED("Closed"),
        FINISHED("Finished");

        private final String description;

        CourseStatus(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }
}
