package org.example.repositories.User;

import org.example.models.users.User.User;

import java.sql.SQLException;

public interface UserDAO {

  User findByLogin(String Login) throws SQLException;
}
