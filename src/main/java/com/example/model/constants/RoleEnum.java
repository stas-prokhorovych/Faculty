package com.example.model.constants;

public enum RoleEnum {
    /**
     * Common role.
     */
    COMMON("Common"),
    /**
     * User role.
     */
    STUDENT("Student"),
    /**
     * Admin role.
     */
    TEACHER("Teacher"),
    /**
     * Admin role.
     */
    ADMIN("Admin");


    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

