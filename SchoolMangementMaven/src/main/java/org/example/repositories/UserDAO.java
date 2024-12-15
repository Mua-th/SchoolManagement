package org.example.repositories;

import org.example.models.user.User;

import java.sql.SQLException;

public interface UserDAO {

  User findByLogin(String Login) throws SQLException;
}
