package org.example.repositories;

import org.example.models.user.User;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorDAO {

  boolean save(User professor) throws SQLException;

  User find(String id ) throws SQLException;

  List<User> getAll() ;
  public boolean delete(int id) ;
}
