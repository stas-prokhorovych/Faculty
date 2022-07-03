package com.example.model.entity;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean userAccess;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUserAccess() {
        return userAccess;
    }

    public void setUserAccess(boolean userAccess) {
        this.userAccess = userAccess;
    }

    public enum Role {
        STUDENT("Student"),
        TEACHER("Teacher"),
        ADMIN("Admin");

        private final String name;

        Role(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder setId(int id) {
            user.setId(id);
            return this;
        }

        public Builder setLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public Builder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder setRole(Role role) {
            user.setRole(role);
            return this;
        }

        public Builder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            user.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder setUserAccess(boolean userAccess) {
            user.setUserAccess(userAccess);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
