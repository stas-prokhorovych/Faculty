package com.example.model.dao;

import com.example.model.db.DataSource;
import com.example.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.model.constants.Query.*;

public interface UserDAO {

    User findUserByLogin(String login);

    void addUser(User user);

    List<User> findTeachers();
}
